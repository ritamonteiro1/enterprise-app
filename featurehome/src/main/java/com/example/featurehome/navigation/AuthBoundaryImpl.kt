package com.example.featurehome.navigation

import android.content.Context
import com.example.featurehome.presentation.HomeActivity
import com.example.navigation.AuthBoundary
import com.example.navigation.UserTokensNavigation


class AuthBoundaryImpl : AuthBoundary {
    override fun navigateToHome(context: Context, userTokensNavigation: UserTokensNavigation) {
        HomeActivity.launch(context, userTokensNavigation)
    }
}