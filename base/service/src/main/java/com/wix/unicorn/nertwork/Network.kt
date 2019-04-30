package com.wix.unicorn.nertwork


import android.content.Context
import android.util.Base64
import com.google.gson.Gson
import com.wix.unicorn.database.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.StringQualifier
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Network {

    val WIX_LOGIN = StringQualifier("wix-login")

    fun Module.network() {
        single { provideGson() }
        single { provideDefaultOkhttpClient() }
        single { provideRetrofit(BuildConfig.BASE_URL, get(), get()) }
        single(WIX_LOGIN) { provideRetrofit(BuildConfig.LOGIN_BASE_URL, get(), get()) }
        single { provideNetworkHandler(get()) }
        single { provideMoviesApi(get()) }
        single { provideLoginApi(get(WIX_LOGIN)) }
        single { LoginRequest(get()) }
        single<MoviesRemoteDataSource> { MoviesRemoteDataSourceImpl(get()) }
    }

    fun provideDefaultOkhttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        val token = Base64.encodeToString("1556620945|OJ-J4VFxD4YZ".toByteArray(), Base64.NO_WRAP)

        builder.addInterceptor { chain ->
            val newRequest = chain.request()
                .newBuilder()
                .build()
            chain.proceed(newRequest)
        }

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }

        return builder.build()
    }

    fun provideGson(): Gson = Gson()

    fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun provideNetworkHandler(context: Context): NetworkHandler {
        return NetworkHandler(context)
    }

    fun provideMoviesApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    fun provideLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

}

