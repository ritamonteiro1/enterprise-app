package com.example.ioasysmvvm.data.data.source

import com.example.ioasysmvvm.data.api.EnterpriseService
import com.example.ioasysmvvm.domain.result.Result
import com.example.ioasysmvvm.domain.enterprise.Enterprise
import com.example.ioasysmvvm.data.api.retrofitWrapper
import com.example.ioasysmvvm.data.repository.EnterpriseRepository

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
