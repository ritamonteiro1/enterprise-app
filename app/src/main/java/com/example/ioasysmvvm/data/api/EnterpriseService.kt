package com.example.ioasysmvvm.data.api

import com.example.ioasysmvvm.constants.Constants
import com.example.ioasysmvvm.data.remote.enterprise.model.EnterpriseListResponse
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