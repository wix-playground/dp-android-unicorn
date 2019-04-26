package com.wix.unicorn.core

import com.wix.unicorn.core.domain.repository.MoviesRepository
import com.wix.unicorn.core.service.repository.MoviesRepositoryImpl
import com.wix.unicorn.database.databaseModule
import com.wix.unicorn.nertwork.network
import org.koin.core.module.Module
import org.koin.dsl.module

val coreModule = module {
    databaseModule()
    network()
    repository()

}

private fun Module.repository() {
    single<MoviesRepository> { MoviesRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }
}
