package com.example.ioasysmvvm.model.domains.enterprise

import com.google.gson.annotations.SerializedName

data class EnterpriseTypeResponse(
    @SerializedName("enterprise_type_name") val enterpriseTypeName: String?
)
