package com.wix.network

import retrofit2.Retrofit


object Network {

    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .build()

    val api = retrofit.create(Api::class.java)

}