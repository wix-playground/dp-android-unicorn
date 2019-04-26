package com.wix.unicorn.database

interface DataBaseProvider<out T> {
    fun provideDataBase(): T
}