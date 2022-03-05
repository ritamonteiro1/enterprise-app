package com.example.ioasysmvvm.domain.exception

import retrofit2.HttpException

enum class ErrorMessageEnum(val value: String) {
    INTERNET_ERROR("Sem conexão com a Internet."),
    DEFAULT_ERROR("Tivemos um problema com nossos servidores, tente novamente mais tarde."),
}

class GenericError(
    override val message: String = ErrorMessageEnum.DEFAULT_ERROR.value
) : Exception(message)

class LoginError : Exception()

class ServerError(
    val httpException: HttpException,
    message: String = ErrorMessageEnum.DEFAULT_ERROR.value
) : Exception(message)