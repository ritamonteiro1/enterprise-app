package com.example.ioasysmvvm.data.model

import com.google.gson.annotations.SerializedName

data class EnterpriseTypeResponse(
    @SerializedName("enterprise_type_name") val enterpriseTypeName: String?
)
