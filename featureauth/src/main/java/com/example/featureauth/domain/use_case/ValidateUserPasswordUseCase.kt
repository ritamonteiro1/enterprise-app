package com.example.featureauth.domain.use_case

import com.example.featureauth.domain.model.PasswordStatus

interface ValidateUserPasswordUseCase {
    fun call(password: String): PasswordStatus
}

private const val MIN_PASSWORD_LENGTH = 8

class ValidateUserPasswordUseCaseImpl : ValidateUserPasswordUseCase {
    override fun call(password: String): PasswordStatus {
        return when {
            password.length >= MIN_PASSWORD_LENGTH -> {
                PasswordStatus.VALID
            }
            password.isEmpty() -> {
                PasswordStatus.EMPTY
            }
            else -> {
                PasswordStatus.INVALID
            }
        }
    }
}