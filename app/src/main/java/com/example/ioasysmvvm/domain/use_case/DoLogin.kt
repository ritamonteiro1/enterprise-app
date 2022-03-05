package com.example.ioasysmvvm.domain.use_case

import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.domain.model.user.User
import com.example.ioasysmvvm.domain.model.user.UserTokens

interface DoLogin {
    suspend fun call(user: User): Result<UserTokens>
}