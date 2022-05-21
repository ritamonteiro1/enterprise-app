package com.example.datasource.api

import com.example.core.constants.Constants
import com.example.datasource.remote.enterprise.model.EnterpriseListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface EnterpriseService {

    @GET("enterprises")
    suspend fun getEnterpriseListResponse(
        @Query(Constants.ENTERPRISE_NAME) enterpriseName: String,
        @Header("access-token") accessToken: String,
        @Header("client") client: String,
        @Header("uid") uid: String
    ): EnterpriseListResponse
}