package com.wix.unicorn.nertwork.dto

data class LoginBody(
        val accept: String,
        val email: String,
        val orgDocID: String,
        val password: String,
        val rememberMe: String,
        val sendEmail: String,
        val sendMail: Boolean
) {
    companion object
}

fun LoginBody.Companion.login(email: String, password: String) = LoginBody(
        "json",
        email,
        "wix-app",
        password,
        "true",
        "false",
        false
)