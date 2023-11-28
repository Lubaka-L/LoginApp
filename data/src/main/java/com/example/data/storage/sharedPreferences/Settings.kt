package com.example.data.storage.sharedPreferences

import com.example.domain.models.Token

interface Settings {

    fun putToken(token: Token)
    fun getToken(): Token?
    fun clear()

}