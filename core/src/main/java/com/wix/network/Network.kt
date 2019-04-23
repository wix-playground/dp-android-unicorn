package com.wix.network


import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Network {

    val modules = module {
        single { provideDefaultOkhttpClient() }
        single { provideRetrofit(get()) }
        single { provideNetworkHandler(get()) }
        single { provideMoviesApi(get()) }
    }

    private fun provideDefaultOkhttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    private fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun provideNetworkHandler(context: Context): NetworkHandler {
        return NetworkHandler(context)
    }

    private fun provideMoviesApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }


}

