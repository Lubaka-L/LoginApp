package com.example.core

import com.google.gson.annotations.SerializedName

data class ResponseWrapper<T>(
    @SerializedName("response")
    val response: T,

    @SerializedName("success")
    val success: Boolean?,

    @SerializedName("error")
    val error: Error?,
) {
    data class Error(
        @SerializedName("error_code")
        val errorCode: Int?,
        @SerializedName("error_msg")
        val errorMsg: String?,
    )

}
