package com.example.ioasysmvvm.data.remote.login.data_source

import com.example.ioasysmvvm.constants.Constants
import com.example.ioasysmvvm.data.api.LoginService
import com.example.ioasysmvvm.data.api.retrofitWrapper
import com.example.ioasysmvvm.data.remote.login.model.UserRequest
import com.example.ioasysmvvm.domain.exception.ServerErrorException
import com.example.ioasysmvvm.domain.exception.UnauthorizedException
import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.domain.model.user.User
import com.example.ioasysmvvm.domain.model.user.UserTokens
import okhttp3.ResponseBody
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection

class LoginRemoteDataSourceImpl(private val loginService: LoginService) : LoginRemoteDataSource {
    override suspend fun doLogin(user: User): Result<UserTokens> {
        val userRequest = UserRequest(user.email, user.password)

        return when (val result = retrofitWrapper { loginService.doLogin(userRequest) }) {
            is Result.Success -> {
                handleSuccessServerResponse(result.data)
            }
            is Result.Error -> result
        }
    }

    private fun handleSuccessServerResponse(response: Response<ResponseBody>) =
        when {
            response.isSuccessful -> {
                Result.Success(
                    UserTokens(
                        accessToken = response.headers()[Constants.HEADER_ACCESS_TOKEN].orEmpty(),
                        client = response.headers()[Constants.HEADER_CLIENT].orEmpty(),
                        uid = response.headers()[Constants.HEADER_UID].orEmpty(),
                    )
                )
            }
            response.code() == HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                Result.Error(UnauthorizedException())
            }
            else -> {
                Result.Error(ServerErrorException())
            }
        }
}