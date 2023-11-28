package com.example.data.repository

import com.example.core.ResponseWrapper
import com.example.core.ResponseExtension.handleResponse
import com.example.core.ResponseExtension.returnSafely
import com.example.core.ResultWrapper
import com.example.data.networking.api.LoginApi
import com.example.data.networking.serialization.requests.TokenRequest
import com.example.data.storage.sharedPreferences.Settings
import com.example.domain.models.Payments
import com.example.domain.models.Token
import com.example.domain.repository.Repository

class RepositoryImpl(private val loginApi: LoginApi, private val settings: Settings) : Repository {

    override suspend fun logIn(login: String, password: String): ResultWrapper<Any> {
        val logInRequest = TokenRequest(login = login, password = password)
        return returnSafely {
            val resToken: ResultWrapper<ResponseWrapper<Token>> =
                loginApi.getToken(logInRequest).handleResponse()
            when (resToken) {
                is ResultWrapper.Success -> {
                    saveToken(resToken.data.response)
                    ResultWrapper.Success("")
                }
                is ResultWrapper.Error -> {
                    ResultWrapper.Error(resToken.exceptionType)
                }
            }
        }
    }

    override suspend fun getPayments(): ResultWrapper<List<Payments>> {
        return returnSafely {
            when (val response = loginApi.getPayments().handleResponse()) {
                is ResultWrapper.Error -> ResultWrapper.Error(response.exceptionType)
                is ResultWrapper.Success -> ResultWrapper.Success(response.data.response)
            }
        }
    }

    override fun logOut() {
        settings.clear()
    }

    private fun saveToken(token: Token) {
        settings.putToken(token)
    }
}
