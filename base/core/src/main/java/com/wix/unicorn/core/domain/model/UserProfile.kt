package com.wix.unicorn.core.domain.model

import com.wix.unicorn.extensions.empty

data class UserProfile(val email: String, val guid: String) {
    companion object {
        fun empty(): UserProfile = UserProfile(String.empty(), String.empty())
    }
}