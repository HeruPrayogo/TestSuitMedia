package com.test.Suitmedia.model


import com.google.gson.annotations.SerializedName

data class SupportX(
    @SerializedName("text")
    val text: String,
    @SerializedName("url")
    val url: String
)