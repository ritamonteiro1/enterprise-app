package com.example.navigation

import android.content.Context

interface AuthBoundary {
    fun navigateToHome(context: Context, userTokensNavigation: UserTokensNavigation)
}