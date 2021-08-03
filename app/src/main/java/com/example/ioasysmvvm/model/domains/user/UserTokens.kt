package com.example.ioasysmvvm.model.domains.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserTokens(
    val accessToken: String,
    val uid: String,
    val client: String
): Parcelable
