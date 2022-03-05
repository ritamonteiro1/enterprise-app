package com.example.ioasysmvvm.data.remote.login.data_source

import com.example.ioasysmvvm.constants.Constants
import com.example.ioasysmvvm.data.api.LoginService
import com.example.ioasysmvvm.data.api.retrofitWrapper
import com.example.ioasysmvvm.data.remote.login.model.UserRequest
import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.domain.model.user.User
import com.example.ioasysmvvm.domain.model.user.UserTokens

class LoginRemoteDataSourceImpl(private val loginService: LoginService) : LoginRemoteDataSource {
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