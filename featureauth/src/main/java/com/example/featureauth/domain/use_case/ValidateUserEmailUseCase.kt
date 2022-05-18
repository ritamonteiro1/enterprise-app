package com.example.featureauth.domain.use_case

import android.util.Patterns
import com.example.featureauth.domain.model.EmailStatus

interface ValidateUserEmailUseCase {
    fun call (email: String): EmailStatus
}

class ValidateUserEmailUseCaseImpl : ValidateUserEmailUseCase {
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