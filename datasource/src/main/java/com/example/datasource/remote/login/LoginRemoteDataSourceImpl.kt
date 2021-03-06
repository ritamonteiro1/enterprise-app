package com.example.datasource.remote.login


import com.example.datasource.api.LoginService
import com.example.datasource.api.retrofitWrapper
import com.example.datasource.constants.Constants
import com.example.datasource.model.UserTokens
import com.example.datasource.model.exceptions.ServerErrorException
import com.example.datasource.model.exceptions.UnauthorizedException
import com.example.datasource.model.user.User
import com.example.datasource.remote.login.model.UserRequest
import com.example.datasource.result.Result
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