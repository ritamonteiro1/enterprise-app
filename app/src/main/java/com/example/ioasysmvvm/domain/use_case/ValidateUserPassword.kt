package com.example.ioasysmvvm.domain.use_case

import com.example.ioasysmvvm.domain.model.user.PasswordStatus

interface ValidateUserPassword {
    fun call(password: String): PasswordStatus
}