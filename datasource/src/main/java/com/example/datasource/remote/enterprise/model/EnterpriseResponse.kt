package com.example.datasource.remote.enterprise.model

import com.google.gson.annotations.SerializedName

data class EnterpriseResponse(
    @SerializedName("enterprise_name") val enterpriseName: String?,
    val photo: String?,
    val description: String?,
    val country: String?,
    @SerializedName("enterprise_type") val enterpriseTypeResponse: EnterpriseTypeResponse?
)
