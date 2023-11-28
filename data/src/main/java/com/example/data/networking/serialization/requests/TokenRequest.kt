package com.example.data.networking.serialization.requests

import com.google.gson.annotations.SerializedName

data class TokenRequest(
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String
)
