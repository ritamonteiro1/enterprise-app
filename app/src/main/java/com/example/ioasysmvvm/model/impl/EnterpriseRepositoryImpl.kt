package com.example.ioasysmvvm.model.impl

import com.example.ioasysmvvm.model.api.EnterpriseService
import com.example.ioasysmvvm.model.domains.result.Result
import com.example.ioasysmvvm.model.domains.enterprise.Enterprise
import com.example.ioasysmvvm.model.repository.EnterpriseRepository

class EnterpriseRepositoryImpl(private val enterpriseService: EnterpriseService) : EnterpriseRepository {
    override suspend fun getEnterpriseList(
        accessToken: String,
        client: String,
        uid: String,
        enterpriseName: String
    ): Result<List<Enterprise>> {
        val result = retrofitWrapper {
            enterpriseService.recoverEnterpriseListResponse(
                accessToken,
                client,
                uid,
                enterpriseName
            )
        }
        return when (result) {
            is Result.Error -> result
            is Result.Success -> Result.Success(result.data.mapToEnterprise())
        }
    }
}
