package com.example.ioasysmvvm.data.data.source

import com.example.ioasysmvvm.domain.result.Result
import com.example.ioasysmvvm.domain.enterprise.Enterprise

interface EnterpriseRemoteDataSource {
    suspend fun getEnterpriseList(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ): Result<List<Enterprise>>
}