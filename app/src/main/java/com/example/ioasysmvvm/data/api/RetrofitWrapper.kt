package com.example.ioasysmvvm.data.api

import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.domain.exception.ErrorMessageEnum
import com.example.ioasysmvvm.domain.exception.GenericError
import com.example.ioasysmvvm.domain.exception.ServerError
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> retrofitWrapper(
    call: suspend () -> T
): Result<T> {
    return try {
        Result.Success(call.invoke())
    } catch (ioException: IOException) {
        Result.Error(GenericError(ErrorMessageEnum.INTERNET_ERROR.value))
    } catch (httpException: HttpException) {
        Result.Error(ServerError(httpException))
    } catch (throwable: Throwable) {
        Result.Error(GenericError())
    }
}
