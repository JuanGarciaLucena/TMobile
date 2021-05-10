package com.juanlucena.tmobile.data.models

import com.google.gson.annotations.SerializedName

data class ErrorResponse (
        @SerializedName("code") val code: Int,
        @SerializedName("status") val status: String
        )