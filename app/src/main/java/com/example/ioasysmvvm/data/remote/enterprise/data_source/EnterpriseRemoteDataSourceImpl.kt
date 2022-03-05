package com.example.ioasysmvvm.data.remote.enterprise.data_source

import com.example.ioasysmvvm.data.api.EnterpriseService
import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.domain.model.enterprise.Enterprise
import com.example.ioasysmvvm.data.api.retrofitWrapper

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
