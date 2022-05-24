package com.example.datasource.remote.enterprise.model


import com.example.datasource.model.enterprise.Enterprise
import com.example.datasource.model.enterprise.EnterpriseType

data class EnterpriseListResponse(val enterprises: List<EnterpriseResponse>?) {
    fun mapToEnterpriseListModel() =
        enterprises?.map { enterpriseResponse -> Enterprise(
                enterpriseResponse.enterpriseName.orEmpty(),
                enterpriseResponse.photo.orEmpty(),
                enterpriseResponse.description.orEmpty(),
                enterpriseResponse.country.orEmpty(),
                EnterpriseType(
                    enterpriseResponse.enterpriseTypeResponse?.enterpriseTypeName.orEmpty()
                )
            )
        } ?: emptyList()
}
