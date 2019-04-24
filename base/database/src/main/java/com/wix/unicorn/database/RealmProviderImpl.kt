package com.wix.unicorn.database

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmProviderImpl(context: Context) : DataBaseProvider<Realm> {

    init {
        Realm.init(context)
        val config = RealmConfiguration.Builder()
            .name("post.realm")
            .schemaVersion(1)
            .build()
        Realm.setDefaultConfiguration(config)

    }

    override fun provideDataBase(): Realm = Realm.getDefaultInstance()
}