package com.example.data.networking.api

import com.example.core.ResponseWrapper
import com.example.data.networking.serialization.requests.TokenRequest
import com.example.domain.models.Payments
import com.example.domain.models.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {

    @POST("/api-test/login")
    suspend fun getToken(@Body tokenRequest: TokenRequest): Response<ResponseWrapper<Token>>

    @GET("/api-test/payments")
    suspend fun getPayments(): Response<ResponseWrapper<List<Payments>>>

}