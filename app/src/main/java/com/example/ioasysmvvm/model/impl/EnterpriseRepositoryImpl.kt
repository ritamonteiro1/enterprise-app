package com.example.ioasysmvvm.model.impl

import com.example.ioasysmvvm.model.api.EnterpriseService
import com.example.ioasysmvvm.model.domains.Result
import com.example.ioasysmvvm.model.domains.enterprise.Enterprise
import com.example.ioasysmvvm.model.domains.enterprise.EnterpriseListResponse
import com.example.ioasysmvvm.model.domains.enterprise.EnterpriseType
import com.example.ioasysmvvm.model.repository.MainRepository

class EnterpriseRepositoryImpl(private val enterpriseService: EnterpriseService) : MainRepository {
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
