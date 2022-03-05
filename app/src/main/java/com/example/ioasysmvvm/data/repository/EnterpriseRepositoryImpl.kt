package com.example.ioasysmvvm.data.repository

import com.example.ioasysmvvm.domain.enterprise.Enterprise
import com.example.ioasysmvvm.domain.result.Result
import com.example.ioasysmvvm.data.data.source.EnterpriseRemoteDataSource
import com.example.ioasysmvvm.data.repository.EnterpriseRepository

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