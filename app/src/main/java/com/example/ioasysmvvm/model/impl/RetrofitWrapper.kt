package com.example.ioasysmvvm.model.impl

import com.example.ioasysmvvm.model.domains.result.Result
import com.example.ioasysmvvm.model.domains.errors.ErrorMessageEnum
import com.example.ioasysmvvm.model.domains.errors.GenericError
import com.example.ioasysmvvm.model.domains.errors.ServerError
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
