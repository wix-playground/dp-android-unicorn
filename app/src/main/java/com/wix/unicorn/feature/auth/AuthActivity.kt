@file:Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")

package com.wix.unicorn.feature.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.wix.unicorn.R
import com.wix.unicorn.base.BaseActivity
import kotlinx.android.synthetic.main.activity_auth.*
import org.koin.android.ext.android.inject


class AuthActivity(override val layoutId: Int = R.layout.activity_auth) : BaseActivity() {

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    private val viewModel: AuthViewModel by inject()

    private val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    private val googleSignInClient: GoogleSignInClient by lazy { GoogleSignIn.getClient(this, gso) }
    private val callbackManager = CallbackManager.Factory.create()

    private val userEmail: TextView get() = a_auth_user_email

    private val googleAuthBtn: View get() = a_auth_google_btn
    private val fbAuthBtn: View get() = a_auth_fb_btn

    private val editLogin: EditText get() = a_auth_edit_login
    private val editPassword: EditText get() = a_auth_edit_password
    private val loginBtn: View get() = a_auth_login_btn

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        initViews()
        viewModel.googleAccount = GoogleSignIn.getLastSignedInAccount(this)
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.handleSignInResult(task)
        }
    }

    private fun initViews() {

        googleAuthBtn.setOnClickListener {
            viewModel.googleSignInClick()
        }
        fbAuthBtn.setOnClickListener {
            viewModel.facebookSignInClick()
        }
        loginBtn.setOnClickListener {
            viewModel.login(editLogin.text.toString(), editPassword.text.toString())
        }
        viewModel.actions.observe(this, Observer { action ->
            when (action) {
                AuthViewModel.Action.GOOGLE_SIGN_IN -> signInGoogle()
                AuthViewModel.Action.GOOGLE_SIGN_OUT -> signOutGoogle()
                AuthViewModel.Action.FACEBOOK_SIGN_IN -> signInFacebook()
                AuthViewModel.Action.FACEBOOK_SIGN_OUT -> signOutFacebook()
            }
        })
        viewModel.userProfile.observe(this, Observer { profile ->
            userEmail.text = profile.email
        })

        viewModel.showLoader.observe(this, Observer { show ->

        })
    }

    private fun signInFacebook() {
        val fbLoginManager = LoginManager.getInstance()
        fbLoginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.w(AuthActivity::class.java.simpleName, "Success login")
            }

            override fun onCancel() {
                Log.w(AuthActivity::class.java.simpleName, "Cancel login")
            }

            override fun onError(e: FacebookException) {
                Log.w(AuthActivity::class.java.simpleName, "Error login: $e")
            }
        })
        fbLoginManager.logInWithReadPermissions(this, listOf("public_profile"))
    }

    private fun signOutFacebook() {

    }

    private fun signInGoogle() {
        startActivityForResult(googleSignInClient.signInIntent, RC_SIGN_IN)
    }

    private fun signOutGoogle() {
        googleSignInClient.signOut()
            .addOnCompleteListener(this) { viewModel.googleAccount = null }
    }

    private fun revokeAccess() {
        googleSignInClient.revokeAccess()
            .addOnCompleteListener(this) { viewModel.googleAccount = null }
    }
}