package com.wix.unicorn.nertwork

import com.wix.unicorn.nertwork.dto.result.BaseResponse
import com.wix.unicorn.nertwork.dto.result.LoginResult
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {

    @FormUrlEncoded
    @POST("oneapp/auth/sign-in")
    fun login(@Field("email") email: String, @Field("password") password: String): Call<BaseResponse<LoginResult>>
}