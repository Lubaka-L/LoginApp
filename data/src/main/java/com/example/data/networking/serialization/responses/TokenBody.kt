package com.example.data.networking.serialization.responses

import com.google.gson.annotations.SerializedName

internal data class TokenBody(
    @SerializedName("token")
    val token: String?
)