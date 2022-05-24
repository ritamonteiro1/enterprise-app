package com.example.ioasysmvvm.domain.use_case

import com.example.ioasysmvvm.domain.model.enterprise.Enterprise
import com.example.ioasysmvvm.domain.model.result.Result

interface GetEnterpriseList {
    suspend fun call(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ): Result<List<Enterprise>>
}