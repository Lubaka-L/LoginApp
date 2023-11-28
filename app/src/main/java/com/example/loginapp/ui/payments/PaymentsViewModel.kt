package com.example.loginapp.ui.payments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Payments
import com.example.domain.repository.Repository
import com.example.core.ResultWrapperUI
import com.example.core.toResultWrapperUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PaymentsViewModel(private val repository: Repository) : ViewModel() {


    private val _payments: MutableStateFlow<ResultWrapperUI<List<Payments>>> =
        MutableStateFlow(ResultWrapperUI.Loading)

    val payments = _payments.asStateFlow()

    init {
        getPayments()
    }

    private fun getPayments(){
        viewModelScope.launch {
            _payments.update {
                repository.getPayments().toResultWrapperUI()
            }
        }
    }

    fun clearToken(){
        repository.logOut()
    }
}