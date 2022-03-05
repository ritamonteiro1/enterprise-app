package com.example.ioasysmvvm.data.api

import com.example.ioasysmvvm.domain.exception.GenericErrorException
import com.example.ioasysmvvm.domain.exception.NetworkErrorException
import com.example.ioasysmvvm.domain.exception.UnauthorizedException
import com.example.ioasysmvvm.domain.exception.ServerErrorException
import com.example.ioasysmvvm.domain.model.result.Result
import retrofit2.HttpException
import java.io.IOException
import javax.net.ssl.HttpsURLConnection

suspend fun <T> retrofitWrapper(
    call: suspend () -> T
): Result<T> {
    return try {
        Result.Success(call.invoke())
    } catch (ioException: IOException) {
        Result.Error(NetworkErrorException())
    } catch (httpException: HttpException) {
        if(httpException.code() == HttpsURLConnection.HTTP_UNAUTHORIZED){
            Result.Error(UnauthorizedException())
        }
        Result.Error(ServerErrorException())
    } catch (throwable: Throwable) {
        Result.Error(GenericErrorException())
    }
}
