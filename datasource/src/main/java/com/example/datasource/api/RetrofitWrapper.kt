package com.example.datasource.api

import com.example.datasource.model.exceptions.GenericErrorException
import com.example.datasource.model.exceptions.NetworkErrorException
import com.example.datasource.model.exceptions.ServerErrorException
import com.example.datasource.result.Result
import retrofit2.HttpException
import java.io.IOException


suspend fun <T> retrofitWrapper(
    call: suspend () -> T
): Result<T> {
    return try {
        Result.Success(call.invoke())
    } catch (ioException: IOException) {
        Result.Error(NetworkErrorException())
    } catch (httpException: HttpException) {
        Result.Error(ServerErrorException())
    } catch (throwable: Throwable) {
        Result.Error(GenericErrorException())
    }
}