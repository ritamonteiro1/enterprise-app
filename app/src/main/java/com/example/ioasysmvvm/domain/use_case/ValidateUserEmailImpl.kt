package com.example.ioasysmvvm.domain.use_case

import android.util.Patterns
import com.example.ioasysmvvm.domain.model.user.EmailStatus

class ValidateUserEmailImpl : ValidateUserEmail {
    override fun call(email: String): EmailStatus {
        return when {
            email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                EmailStatus.VALID
            }
            email.isEmpty() -> {
                EmailStatus.EMPTY
            }
            else -> {
                EmailStatus.INVALID
            }
        }
    }
}