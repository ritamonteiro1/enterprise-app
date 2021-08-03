package com.example.ioasysmvvm.model.api


import com.example.ioasysmvvm.model.domains.user.UserRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

import retrofit2.http.*

interface LoginService {
    @POST("users/auth/sign_in")
    suspend fun doLogin(@Body userRequest: UserRequest): Response<ResponseBody>
}