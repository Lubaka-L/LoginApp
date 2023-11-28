package com.example.domain.models

import java.time.LocalDateTime

data class Payments(
    val id: Int,
    val title: String,
    val amount: String?,
    val created: LocalDateTime
)
