package com.wix.unicorn.feature.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.wix.unicorn.base.BaseViewModel

class AuthViewModel : BaseViewModel() {

    private val _actions: MutableLiveData<Action> = MutableLiveData()
    var googleAccount: GoogleSignInAccount? = null

    val actions: LiveData<Action> get() = _actions

    enum class Action {
        GOOGLE_SIGN_IN, GOOGLE_SIGN_OUT
    }

    fun googleSignInClick() {
        _actions.value = Action.GOOGLE_SIGN_IN
    }

    fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            googleAccount = account
            // Signed in successfully, show authenticated UI.
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(this.toString(), "signInResult:failed code=$e")
        }

    }

}