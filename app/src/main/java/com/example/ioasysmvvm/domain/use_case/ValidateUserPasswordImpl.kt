package com.example.ioasysmvvm.domain.use_case

import com.example.ioasysmvvm.domain.model.user.PasswordStatus

private const val MIN_PASSWORD_LENGTH = 8

class ValidateUserPasswordImpl : ValidateUserPassword {
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