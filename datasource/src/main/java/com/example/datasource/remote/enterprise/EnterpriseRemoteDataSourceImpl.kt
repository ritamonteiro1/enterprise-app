package com.example.datasource.remote.enterprise

import com.example.datasource.api.EnterpriseService
import com.example.datasource.api.retrofitWrapper
import com.example.datasource.model.enterprise.Enterprise
import com.example.datasource.result.Result

class EnterpriseRemoteDataSourceImpl(private val enterpriseService: EnterpriseService) :
    EnterpriseRemoteDataSource {
    override suspend fun getEnterpriseList(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ): Result<List<Enterprise>> {
        val result = retrofitWrapper {
            enterpriseService.getEnterpriseListResponse(
                enterpriseName,
                accessToken,
                client,
                uid
            )
        }
        return when (result) {
            is Result.Error -> result
            is Result.Success -> Result.Success(result.data.mapToEnterpriseListModel())
        }
    }
}