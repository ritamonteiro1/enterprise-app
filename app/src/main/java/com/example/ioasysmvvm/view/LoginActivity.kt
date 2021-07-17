package com.example.ioasysmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.ioasysmvvm.R
import com.example.ioasysmvvm.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModel = ViewModelProvider.NewInstanceFactory().create(LoginViewModel::class.java)
    }
}