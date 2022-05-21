package com.example.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserTokensNavigation(
    val accessToken: String,
    val uid: String,
    val client: String
) : Parcelable