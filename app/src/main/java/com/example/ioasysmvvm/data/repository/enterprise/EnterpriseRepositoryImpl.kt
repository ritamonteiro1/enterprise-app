package com.example.ioasysmvvm.data.repository.enterprise

import com.example.ioasysmvvm.domain.model.enterprise.Enterprise
import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.data.remote.enterprise.data_source.EnterpriseRemoteDataSource
import com.example.ioasysmvvm.domain.repository.enterprise.EnterpriseRepository

class EnterpriseRepositoryImpl(private val enterpriseRemoteDataSource: EnterpriseRemoteDataSource) :
    EnterpriseRepository {
    override suspend fun getEnterpriseList(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ): Result<List<Enterprise>> {
        return enterpriseRemoteDataSource.getEnterpriseList(enterpriseName, accessToken, client, uid)
    }
}