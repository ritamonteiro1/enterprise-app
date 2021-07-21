package com.example.ioasysmvvm.model.api

import com.example.ioasysmvvm.model.constants.Constants
import com.example.ioasysmvvm.model.domains.enterprise.EnterpriseListResponse
import com.example.ioasysmvvm.model.domains.user.UserRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface DataService {

    @POST("users/auth/sign_in")
    suspend fun recoverVerifyLogin(@Body userRequest: UserRequest): Boolean

    @GET("enterprises")
    fun recoverEnterpriseListResponse(
        @Query(Constants.ENTERPRISE_NAME) enterpriseName: String,
        @Header("access-token") accessToken: String,
        @Header("client") client: String,
        @Header("uid") uid: String
    ): Call<EnterpriseListResponse>
}