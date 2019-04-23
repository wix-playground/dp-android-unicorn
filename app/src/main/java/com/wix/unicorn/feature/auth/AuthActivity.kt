@file:Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")

package com.wix.unicorn.feature.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
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

    private val authBtn: View get() = a_auth_google_btn

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        initViews()
    }

    override fun onStart() {
        super.onStart()
        viewModel.googleAccount = GoogleSignIn.getLastSignedInAccount(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.handleSignInResult(task)
        }
    }

    private fun initViews() {

        authBtn.setOnClickListener {
            viewModel.googleSignInClick()
        }

        viewModel.actions.observe(this, Observer { action ->
            when (action) {
                AuthViewModel.Action.GOOGLE_SIGN_IN -> signIn()
                AuthViewModel.Action.GOOGLE_SIGN_OUT -> signOut()
            }
        })

    }


    private fun signIn() {
        startActivityForResult(googleSignInClient.signInIntent, RC_SIGN_IN)
    }

    private fun signOut() {
        googleSignInClient.signOut()
            .addOnCompleteListener(this, OnCompleteListener<Void> {
                viewModel.googleAccount = null
            })
    }
    // [END signOut]

    // [START revokeAccess]
    private fun revokeAccess() {
        googleSignInClient.revokeAccess()
            .addOnCompleteListener(this, OnCompleteListener<Void> {
                viewModel.googleAccount = null
            })
    }
}