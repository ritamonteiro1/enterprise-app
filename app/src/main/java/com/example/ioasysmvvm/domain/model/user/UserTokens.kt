package com.example.ioasysmvvm.domain.model.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserTokens(
    val accessToken: String,
    val uid: String,
    val client: String
): Parcelable