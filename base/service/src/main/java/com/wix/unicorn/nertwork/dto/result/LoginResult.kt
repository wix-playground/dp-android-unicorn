package com.wix.unicorn.nertwork.dto.result

data class LoginResult(
        val email: String,
        val guid: String,
        val hasPassword: Boolean,
        val language: String,
        val mailStatus: String,
        val memberId: Any,
        val permissions: Int,
        val postRegColors: Any,
        val regColor: Any,
        val status: String,
        val uaToken: String,
        val userName: String,
        val wixStaff: Boolean
)