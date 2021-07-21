package com.example.ioasysmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.ioasysmvvm.R
import com.example.ioasysmvvm.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModel()
    private var loginEmailTextInputLayout: TextInputLayout? = null
    private var loginEmailEditText: EditText? = null
    private var loginPasswordTextInputLayout: TextInputLayout? = null
    private var loginPasswordEditText: EditText? = null
    private var loginButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewsById()
    }

    private fun findViewsById() {
        loginEmailEditText = findViewById(R.id.loginEmailEditText)
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText)
        loginButton = findViewById(R.id.loginButton)
        loginEmailTextInputLayout = findViewById(R.id.loginEmailTextInputLayout)
        loginPasswordTextInputLayout = findViewById(R.id.loginPasswordTextInputLayout)    }
}