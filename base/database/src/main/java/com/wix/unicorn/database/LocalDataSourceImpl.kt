package com.wix.unicorn.database

import io.realm.Realm
import io.realm.kotlin.where

class LocalDataSourceImpl(private val dataBaseProvider: DataBaseProvider<Realm>) : LocalDataSource {

    override fun save(movieDb: List<MovieDb>) {
        dataBaseProvider.provideDataBase().use {
            it.executeTransaction { realm ->
                realm.copyToRealmOrUpdate(movieDb)
            }
        }
    }

    override fun read(): List<MovieDb> {
        return dataBaseProvider.provideDataBase().where<MovieDb>().findAll() ?: emptyList()
    }
}