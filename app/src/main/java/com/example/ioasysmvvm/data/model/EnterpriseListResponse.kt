package com.example.ioasysmvvm.data.model

import com.example.ioasysmvvm.domain.enterprise.Enterprise
import com.example.ioasysmvvm.domain.enterprise.EnterpriseType

data class EnterpriseListResponse(val enterprises: List<EnterpriseResponse>?) {
    fun mapToEnterpriseListModel() =
        enterprises?.map { enterpriseResponse ->
            Enterprise(
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
