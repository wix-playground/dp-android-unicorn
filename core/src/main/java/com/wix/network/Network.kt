package com.wix.network


import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Network(nameSpace: String) {

    private val nameSpaceQualifier = StringQualifier(nameSpace)

    val modules = module {
        single { provideRetrofit(get(nameSpaceQualifier)) }
        single { provideDefaultOkhttpClient() }
        single { provideNetworkHandler(get(nameSpaceQualifier)) }
    }

    fun provideDefaultOkhttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideNetworkHandler(context: Context): NetworkHandler {
        return NetworkHandler(context)
    }


}

