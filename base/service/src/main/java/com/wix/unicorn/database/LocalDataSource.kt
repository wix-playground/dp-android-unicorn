package com.wix.unicorn.database

interface LocalDataSource {
    fun save(movieDb: List<MovieDb>)
    fun read(): List<MovieDb>
}