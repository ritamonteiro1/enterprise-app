package com.example.ioasysmvvm.model.repository

import com.example.ioasysmvvm.model.domains.result.Result
import com.example.ioasysmvvm.model.domains.enterprise.Enterprise

interface MainRepository {
    suspend fun getEnterpriseList(
        accessToken: String,
        client: String,
        uid: String,
        enterpriseName: String
    ): Result<List<Enterprise>>
}