package com.example.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

object ResponseExtension {

    /** Обрабатывает [retrofit2.Response] и возвращает ResultWrapper */
    fun <T : Any> Response<T>.handleResponse(): ResultWrapper<T> {
        return try {
            if (isSuccessful) {
                return (this.body() as ResponseWrapper<*>).let { wrapper ->
                    if (wrapper.success == true) {
                        this.body()?.let { body ->
                            ResultWrapper.Success(body)
                        } ?: ResultWrapper.Error(ExceptionType.EmptyBodyReceivedException)
                    } else {
                        when (wrapper.error?.errorCode) {
                            1003 -> ResultWrapper.Error(ExceptionType.InvalidCredentialsException)
                            else -> ResultWrapper.Error(ExceptionType.UnspecifiedException)
                        }
                    }
                }
            } else {
                ResultWrapper.Error(ExceptionType.ServerException)
            }
        } catch (exception: Exception) {
            ResultWrapper.Error(ExceptionType.UnspecifiedException)
        }
    }

    suspend fun <T : Any> returnSafely(
        scope: CoroutineDispatcher = Dispatchers.IO,
        content: suspend CoroutineDispatcher.() -> ResultWrapper<T>
    ): ResultWrapper<T> {
        return try {
            content.invoke(scope)
        } catch (exception: Exception) {
            ResultWrapper.Error(ExceptionType.UnspecifiedException)
        }
    }
}