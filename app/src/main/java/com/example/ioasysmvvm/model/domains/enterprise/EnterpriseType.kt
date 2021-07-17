package com.example.ioasysmvvm.model.domains.enterprise

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EnterpriseType(
    @SerializedName("enterprise_type_name") val enterpriseTypeName: String
) : Serializable
