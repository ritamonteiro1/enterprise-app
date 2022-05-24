package com.example.ioasysmvvm.domain.use_case

import com.example.ioasysmvvm.domain.model.user.EmailStatus

interface ValidateUserEmail {
    fun call (email: String): EmailStatus
}