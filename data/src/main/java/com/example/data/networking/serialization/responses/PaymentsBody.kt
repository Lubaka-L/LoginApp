package com.example.data.networking.serialization.responses

import com.google.gson.annotations.SerializedName

internal data class PaymentsBody(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("amount")
    val amount: Any?,

    @SerializedName("created")
    val created: Long?
)