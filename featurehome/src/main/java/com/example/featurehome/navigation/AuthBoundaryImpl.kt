package com.example.featurehome.navigation

import android.app.Activity
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.example.core.constants.Constants
import com.example.featurehome.presentation.HomeActivity
import com.example.navigation.AuthBoundary
import com.example.navigation.UserTokensNavigation


class AuthBoundaryImpl : AuthBoundary {
    override fun navigateToHome(activity: Activity?, userTokensNavigation: UserTokensNavigation) {
        val intent = Intent(activity, HomeActivity::class.java)
        intent.putExtra(Constants.HEADER_ACCESS_TOKEN, userTokensNavigation.accessToken)
        intent.putExtra(Constants.HEADER_UID, userTokensNavigation.uid)
        intent.putExtra(Constants.HEADER_CLIENT, userTokensNavigation.client)
        startActivity(intent)
        finish()
    }
}