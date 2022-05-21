package com.example.datasource.repository.enterprise

import com.example.datasource.model.enterprise.Enterprise
import com.example.datasource.result.Result
import com.example.datasource.remote.enterprise.EnterpriseRemoteDataSource

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