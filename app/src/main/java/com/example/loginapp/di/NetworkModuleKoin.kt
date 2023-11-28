package com.example.loginapp.di

import com.example.data.networking.api.LoginApi
import com.example.data.networking.interceptors.AuthInterceptor
import com.example.data.networking.serialization.adapters.PaymentsBodyAdapter
import com.example.data.networking.serialization.adapters.TokenBodyAdapter
import com.example.domain.models.Payments
import com.example.domain.models.Token
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://easypay.world"

val networkModule: Module = module {
    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single<AuthInterceptor> {
        AuthInterceptor(get())
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<AuthInterceptor>())
            .build()
    }

    single<Gson> {
        GsonBuilder()
            .registerTypeAdapter(
                Payments::class.java,
                PaymentsBodyAdapter()
            ).registerTypeAdapter(
                Token::class.java,
                TokenBodyAdapter()
            ).setLenient()
            .create()
    }

    single<Retrofit.Builder> {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
    }

    single<LoginApi> {
        get<Retrofit.Builder>().baseUrl(BASE_URL).build().create(LoginApi::class.java)
    }
}

