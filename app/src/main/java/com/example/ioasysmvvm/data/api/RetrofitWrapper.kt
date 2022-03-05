package com.example.ioasysmvvm.data.api

import com.example.ioasysmvvm.domain.exception.GenericError
import com.example.ioasysmvvm.domain.exception.LoginError
import com.example.ioasysmvvm.domain.exception.ServerError
import com.example.ioasysmvvm.domain.model.result.Result
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> retrofitWrapper(
    call: suspend () -> T
): Result<T> {
    return try {
        Result.Success(call.invoke())
    } catch (userNotAuthenticatedException: Exception) {
        Result.Error(LoginError())
    } catch (ioException: IOException) {
        Result.Error(GenericError())
    } catch (httpException: HttpException) {
        Result.Error(ServerError())
    } catch (throwable: Throwable) {
        Result.Error(GenericError())
    }
}
