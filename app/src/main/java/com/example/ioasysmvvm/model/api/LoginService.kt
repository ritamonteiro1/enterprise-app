package com.example.ioasysmvvm.model.api


import com.example.ioasysmvvm.model.domains.user.UserRequest

import retrofit2.http.*

interface LoginService {

    @POST("users/auth/sign_in")
    suspend fun recoverVerifyLogin(@Body userRequest: UserRequest): Boolean
}