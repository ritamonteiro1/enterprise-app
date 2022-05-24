package com.example.ioasysmvvm.domain.model.enterprise

import java.io.Serializable

data class Enterprise(
    val enterpriseName: String,
    val photo: String,
    val description: String,
    val country: String,
    val enterpriseType: EnterpriseType
): Serializable
