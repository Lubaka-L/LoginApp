package com.example.loginapp.di

import com.example.loginapp.ui.login.LoginViewModel
import com.example.loginapp.ui.payments.PaymentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { LoginViewModel(get(), get()) }
    viewModel { PaymentsViewModel(get()) }

}