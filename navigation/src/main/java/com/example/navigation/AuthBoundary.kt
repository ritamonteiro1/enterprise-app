package com.example.navigation

import android.app.Activity

interface AuthBoundary {
    fun navigateToHome(activity: Activity?, userTokensNavigation: UserTokensNavigation)
}