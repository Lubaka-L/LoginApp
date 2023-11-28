package com.example.core

sealed class ResultWrapper<T> {
    data class Success<T>(val data: T) : ResultWrapper<T>()
    data class Error<T>(val exceptionType: ExceptionType) : ResultWrapper<T>()
}

enum class ExceptionType {
    InvalidCredentialsException,
    EmptyBodyReceivedException,
    UnspecifiedException,
    ServerException,
}

sealed class ResultWrapperUI<out T> {
    data class Success<T>(val data: T) : ResultWrapperUI<T>()
    data class Error<T>(val exceptionType: ExceptionType) : ResultWrapperUI<T>()
    object Loading : ResultWrapperUI<Nothing>()
}


fun <T> ResultWrapper<T>.toResultWrapperUI(): ResultWrapperUI<T> {
    return when (this) {
        is ResultWrapper.Success -> ResultWrapperUI.Success(data)
        is ResultWrapper.Error -> ResultWrapperUI.Error(exceptionType)
    }
}

fun <T> ResultWrapperUI<T>.toResultWrapper(): ResultWrapper<T>? {
    return when (this) {
        is ResultWrapperUI.Error -> ResultWrapper.Error(exceptionType)
        is ResultWrapperUI.Success -> ResultWrapper.Success(data)
        ResultWrapperUI.Loading -> null
    }
}