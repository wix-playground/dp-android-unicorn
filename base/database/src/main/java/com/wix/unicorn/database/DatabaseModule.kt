package com.wix.unicorn.database

import io.realm.Realm
import org.koin.core.module.Module

fun Module.databaseModule() {
    single<DataBaseProvider<Realm>> { RealmProviderImpl(get()) }
    single<LocalDataSource> { LocalDataSourceImpl(get()) }
}