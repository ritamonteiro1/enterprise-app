package com.example.ioasysmvvm.data.remote.enterprise.data_source

import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.domain.model.enterprise.Enterprise

interface EnterpriseRemoteDataSource {
    suspend fun getEnterpriseList(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ): Result<List<Enterprise>>
}