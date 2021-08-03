package com.example.ioasysmvvm.model.impl

import com.example.ioasysmvvm.model.api.LoginService
import com.example.ioasysmvvm.model.constants.Constants
import com.example.ioasysmvvm.model.domains.Result
import com.example.ioasysmvvm.model.domains.user.UserTokens
import com.example.ioasysmvvm.model.domains.user.User
import com.example.ioasysmvvm.model.domains.user.UserRequest
import com.example.ioasysmvvm.model.repository.LoginRepository

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