package com.example.ioasysmvvm.model.api

import com.example.ioasysmvvm.model.constants.Constants
import com.example.ioasysmvvm.model.domains.enterprise.EnterpriseListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface EnterpriseService {

    @GET("enterprises")
    fun recoverEnterpriseListResponse(
        @Query(Constants.ENTERPRISE_NAME) enterpriseName: String,
        @Header("access-token") accessToken: String,
        @Header("client") client: String,
        @Header("uid") uid: String
    ): Call<EnterpriseListResponse>
}