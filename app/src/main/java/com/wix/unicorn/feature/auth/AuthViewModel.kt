package com.wix.unicorn.feature.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.wix.unicorn.base.BaseViewModel
import com.wix.unicorn.core.domain.model.UserProfile
import com.wix.unicorn.optionals.Failure
import kotlinx.coroutines.launch

class AuthViewModel(
    val getUserProfileUseCase: GetProfileUseCase,
    val loginUseCase: WixLoginUseCase,
    val logoutUserCase: LogoutUseCase
) : BaseViewModel() {

    private val _actions: MutableLiveData<Action> = MutableLiveData()
    val actions: LiveData<Action> get() = _actions

    private val _userProfile: MutableLiveData<UserProfile> = MutableLiveData()
    val userProfile: LiveData<UserProfile> get() = _userProfile

    private val _showLoader: MutableLiveData<Boolean> = MutableLiveData()
    val showLoader: LiveData<Boolean> get() = _showLoader

    var googleAccount: GoogleSignInAccount? = null


    enum class Action {
        GOOGLE_SIGN_IN,
        GOOGLE_SIGN_OUT,
        FACEBOOK_SIGN_IN,
        FACEBOOK_SIGN_OUT
    }

    fun onStart() {
        viewModelScope.launch {
            getUserProfileUseCase(Unit) {
                it.either(::handleFailure) { profile ->
                    _showLoader.value = false
                    _userProfile.value = profile
                    it
                }
            }
        }
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

    fun facebookSignInClick() {
        _actions.value = Action.FACEBOOK_SIGN_IN
    }

    override fun handleFailure(failure: Failure) {
        when (failure) {
            is Failure.UserNotAutorized -> {
                _showLoader.value = false
            }
            else -> super.handleFailure(failure)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(WixLoginUseCase.Params(email, password)) { either ->
                either.either(::handleFailure) {

                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUserCase(Unit) {
                it.either(::handleFailure) {
                    _userProfile.value = null
                    it
                }
            }
        }
    }
}