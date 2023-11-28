package com.example.data.networking.serialization.adapters

import com.example.data.networking.serialization.responses.PaymentsBody
import com.example.domain.models.Payments
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class PaymentsBodyAdapter : JsonDeserializer<Payments> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Payments {
        val body = context?.deserialize<PaymentsBody>(json, PaymentsBody::class.java)
        val data = LocalDateTime.ofInstant(Instant.ofEpochSecond(body?.created ?: 0), ZoneId.systemDefault())
        return Payments(
            id = body?.id ?:0,
            title = body?.title.orEmpty(),
            amount = if (body?.amount.toString() == "null") null else body?.amount.toString(),
            created = data
        )
    }
}