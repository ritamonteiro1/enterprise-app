package com.example.ioasysmvvm.model.domains.enterprise

data class EnterpriseListResponse(val enterprises: List<EnterpriseResponse>?) {
    fun mapToEnterprise() =
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
