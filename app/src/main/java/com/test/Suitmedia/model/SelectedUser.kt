package com.test.Suitmedia.model


import com.google.gson.annotations.SerializedName

data class SelectedUser(
    @SerializedName("data")
    val data: DataX,
    @SerializedName("support")
    val support: SupportX
)