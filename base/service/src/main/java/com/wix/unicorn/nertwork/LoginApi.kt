package com.wix.unicorn.nertwork

import com.wix.unicorn.nertwork.dto.LoginBody
import com.wix.unicorn.nertwork.dto.result.BaseResponse
import com.wix.unicorn.nertwork.dto.result.LoginResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("oneapp/auth/sign-in")
    fun login(@Body body: LoginBody): Call<BaseResponse<LoginResult>>
}