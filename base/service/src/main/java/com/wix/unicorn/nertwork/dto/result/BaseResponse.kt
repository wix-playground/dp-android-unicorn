package com.wix.unicorn.nertwork.dto.result

data class BaseResponse<out T>(
        val errorCode: Int,
        val errorDescription: String,
        val payload: T,
        val success: Boolean
)