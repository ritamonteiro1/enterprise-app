package com.example.ioasysmvvm.data.model

import com.google.gson.annotations.SerializedName

data class EnterpriseResponse(
    @SerializedName("enterprise_name") val enterpriseName: String?,
    val photo: String?,
    val description: String?,
    val country: String?,
    @SerializedName("enterprise_type") val enterpriseTypeResponse: EnterpriseTypeResponse?
)
