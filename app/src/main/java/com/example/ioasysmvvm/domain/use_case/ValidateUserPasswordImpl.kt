package com.example.ioasysmvvm.domain.use_case

import com.example.ioasysmvvm.domain.model.user.PasswordStatus

class ValidateUserPasswordImpl : ValidateUserPassword {
    override fun call(password: String): PasswordStatus {
        return when {

            password.length >= 8 -> {
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