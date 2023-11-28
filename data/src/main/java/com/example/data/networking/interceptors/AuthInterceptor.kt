package com.example.data.networking.interceptors

import com.example.data.BuildConfig
import com.example.data.storage.sharedPreferences.Settings
import com.example.domain.repository.Repository
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val settings: Settings) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val token = settings.getToken()
        if (token != null) {
            requestBuilder.addHeader(
                "token",
                token.token
            )
        }

        requestBuilder.addHeader(
            "app-key",
            BuildConfig.App_Key
        )

        requestBuilder.addHeader(
            "v",
            "1"
        )

        return chain.proceed(requestBuilder.build())
    }
}