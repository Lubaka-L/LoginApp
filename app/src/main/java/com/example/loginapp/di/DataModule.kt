package com.example.loginapp.di

import com.example.data.repository.RepositoryImpl
import com.example.data.storage.sharedPreferences.Settings
import com.example.data.storage.sharedPreferences.SettingsImpl
import com.example.domain.repository.Repository
import org.koin.dsl.module

val dataModule = module {
    single<Repository> { RepositoryImpl(get(), get()) }
    single<Settings> { SettingsImpl(get()) }
}