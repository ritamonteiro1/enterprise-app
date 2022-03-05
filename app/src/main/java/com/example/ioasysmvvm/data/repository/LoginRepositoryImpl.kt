package com.example.ioasysmvvm.data.repository

import com.example.ioasysmvvm.constants.Constants
import com.example.ioasysmvvm.data.api.LoginService
import com.example.ioasysmvvm.data.api.retrofitWrapper
import com.example.ioasysmvvm.data.model.UserRequest
import com.example.ioasysmvvm.domain.result.Result
import com.example.ioasysmvvm.domain.user.User
import com.example.ioasysmvvm.domain.user.UserTokens

class LoginRepositoryImpl(private val loginService: LoginService) : LoginRepository {
    override suspend fun doLogin(user: User): Result<UserTokens> {
        val userRequest = UserRequest(user.email, user.password)

        return when (val result = retrofitWrapper { loginService.doLogin(userRequest) }) {
            is Result.Success -> {
                Result.Success(
                    UserTokens(
                        accessToken = result.data.headers()[Constants.HEADER_ACCESS_TOKEN].orEmpty(),
                        uid = result.data.headers()[Constants.HEADER_UID].orEmpty(),
                        client = result.data.headers()[Constants.HEADER_CLIENT].orEmpty()
                    )
                )
            }
            is Result.Error -> result
        }
    }
}