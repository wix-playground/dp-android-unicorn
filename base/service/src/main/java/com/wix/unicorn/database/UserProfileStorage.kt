package com.wix.unicorn.database

import android.content.SharedPreferences
import com.google.gson.Gson
import java.io.Serializable

class UserProfileStorage(
    private val gson: Gson,
    private val sharedPreferences: SharedPreferences
) {

    companion object {
        private const val K_PROFILE = "profile"
    }

    var profile: UserProfile?
        get() =
            gson.fromJson<UserProfile>(
                sharedPreferences.getString(K_PROFILE, ""),
                UserProfile::class.java
            )
        set(value) {
            sharedPreferences
                .edit()
                .putString(K_PROFILE, gson.toJson(value, UserProfile::class.java))
                .apply()
        }

    data class UserProfile(
        val email: String,
        val password: String,
        val guid: String,
        val uaToken: String
    ) : Serializable

}