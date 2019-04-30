package com.wix.unicorn.nertwork


import android.content.Context
import com.google.gson.Gson
import com.wix.unicorn.database.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.StringQualifier
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy


object Network {

    val WIX_LOGIN = StringQualifier("wix-login")
    private val XSRF_TOKEN = "XSRF-TOKEN"
    private val X_XSRF_TOKEN_KEY = "X-XSRF-TOKEN"

    fun Module.network() {
        single { provideGson() }
        single { provideCookieJar() }
        single { provideDefaultOkhttpClient() }
        single { provideRetrofit(BuildConfig.BASE_URL, get(), get()) }
        single(WIX_LOGIN) { provideRetrofit(BuildConfig.LOGIN_BASE_URL, get(), get()) }
        single { provideNetworkHandler(get()) }
        single { provideMoviesApi(get()) }
        single { provideLoginApi(get(WIX_LOGIN)) }
        single { LoginRequest(get()) }
        single { LogoutRequest(get()) }
        single<MoviesRemoteDataSource> { MoviesRemoteDataSourceImpl(get()) }
    }

    private fun provideCookieJar(): CookieJar {
        return object : CookieJar {

            private val cookieStore = HashMap<String, MutableList<Cookie>>()

            override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
                cookieStore[url.host()] = cookies
            }

            override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
                val cookies = cookieStore[url.host()]
                return cookies ?: ArrayList()
            }
        }
    }

    fun provideDefaultOkhttpClient(): OkHttpClient {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        val cookieJar = JavaNetCookieJar(cookieManager)
        val builder = OkHttpClient.Builder()
            .cookieJar(cookieJar)


        builder.addInterceptor { chain ->
            val request = chain.request()

            val cookies: List<Cookie> = cookieJar.loadForRequest(request.url())
            cookies
                .find { it.name() == XSRF_TOKEN }
                ?.let {
                    chain.proceed(
                        request.newBuilder()
                            .header(X_XSRF_TOKEN_KEY, it.value())
                            .build()
                    )
                }
            chain.proceed(request)
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

    fun provideLoginApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

}

