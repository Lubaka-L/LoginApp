package com.example.domain.repository

import com.example.core.ResultWrapper
import com.example.domain.models.Payments

interface Repository {
    suspend fun logIn(login: String, password: String): ResultWrapper<Any>
    suspend fun getPayments(): ResultWrapper<List<Payments>>

    fun logOut()
}