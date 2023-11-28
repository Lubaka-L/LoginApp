package com.example.data.storage.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.domain.models.Token


class SettingsImpl(context: Context) : Settings {
    private val tokenName = "tokenName"

    private val preferences: SharedPreferences =
        context.getSharedPreferences("PrefTable", Context.MODE_PRIVATE)

    override fun putToken(token: Token) {
        preferences.edit().apply {
            putString(tokenName, token.token)
        }.apply()
    }

    override fun getToken(): Token? {
        val name = preferences.getString(tokenName, "")
        return if (name.isNullOrBlank()){
            null
        } else Token(name)
    }

    override fun clear() {
        preferences.edit().remove(tokenName).commit()
    }


}