package com.example.featureauth.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.featureauth.R
import com.example.navigation.AuthBoundary
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : ComponentActivity() {

    private val boundary: AuthBoundary by inject()
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginActivityContent(viewModel, boundary)
        }
    }

    @Composable
    fun LoginActivityContent(viewModel: LoginViewModel, boundary: AuthBoundary) {
        val scrollState = rememberScrollState()
        Scaffold(
            backgroundColor = colorResource(R.color.beige)
        ) {
            Column(
                modifier = Modifier.scrollable(
                    state = scrollState,
                    orientation = Orientation.Vertical
                )
            ) {
                Spacer(modifier = Modifier.paddingFromBaseline(top = 62.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_home),
                    contentDescription = stringResource(id = R.string.login_logo_content_description)
                )
            }
        }
    }

    @Composable
    @Preview
    fun LoginActivityContentPreview() {
        Image(
            painter = painterResource(id = R.drawable.img_home),
            contentDescription = ""
        )
    }
}