package com.example.data.networking.serialization.adapters

import com.example.data.networking.serialization.responses.TokenBody
import com.example.domain.models.Token
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class TokenBodyAdapter : JsonDeserializer<Token> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Token {
        val body = context?.deserialize<TokenBody>(json, TokenBody::class.java)
        return Token(
            token = body?.token.orEmpty()
        )
    }
}