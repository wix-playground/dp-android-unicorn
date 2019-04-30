package com.wix.unicorn.nertwork

import org.koin.core.module.Module

fun Module.network() {
    single { Network.provideDefaultOkhttpClient() }
    single { Network.provideRetrofit(get()) }
    single { Network.provideNetworkHandler(get()) }
    single { Network.provideMoviesApi(get()) }
    single<MoviesRemoteDataSource> { MoviesRemoteDataSourceImpl(get()) }

}