package com.example.ioasysmvvm.model.impl

import com.example.ioasysmvvm.model.api.EnterpriseService
import com.example.ioasysmvvm.model.domains.enterprise.Enterprise
import com.example.ioasysmvvm.model.domains.enterprise.EnterpriseType
import com.example.ioasysmvvm.model.repository.MainRepository

class MainRepositoryImpl(private val enterpriseService: EnterpriseService) : MainRepository {
    private val enterpriseListResponse = enterpriseService.recoverEnterpriseListResponse()
    override suspend fun getEnterpriseList(): List<Enterprise> =
        enterpriseListResponse.enterprises?.map { enterpriseResponse ->
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
