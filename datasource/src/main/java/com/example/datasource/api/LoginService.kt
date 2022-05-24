package com.example.datasource.api


import com.example.datasource.remote.login.model.UserRequest
import okhttp3.ResponseBody
import retrofit2.Response

import retrofit2.http.*

interface LoginService {
    @POST("users/auth/sign_in")
    suspend fun doLogin(@Body userRequest: UserRequest): Response<ResponseBody>
}