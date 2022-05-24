package com.example.datasource.remote.enterprise.model

import com.google.gson.annotations.SerializedName

data class EnterpriseTypeResponse(
    @SerializedName("enterprise_type_name") val enterpriseTypeName: String?
)
