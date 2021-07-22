package com.example.ioasysmvvm.model.impl

import com.example.ioasysmvvm.model.api.EnterpriseService
import com.example.ioasysmvvm.model.domains.enterprise.Enterprise
import com.example.ioasysmvvm.model.domains.enterprise.EnterpriseType
import com.example.ioasysmvvm.model.repository.MainRepository

class MainRepositoryImpl(private val enterpriseService: EnterpriseService) : MainRepository {
    override suspend fun getEnterpriseList(
        accessToken: String,
        client: String,
        uid: String,
        enterpriseName: String
    ): List<Enterprise> {
        val enterpriseListResponse = enterpriseService.recoverEnterpriseListResponse(
            accessToken,
            client,
            uid,
            enterpriseName
        )
        return enterpriseListResponse.enterprises?.map { enterpriseResponse ->
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
}
