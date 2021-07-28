package com.example.ioasysmvvm.view

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ioasysmvvm.R
import com.example.ioasysmvvm.databinding.ActivityLoginBinding
import com.example.ioasysmvvm.model.constants.Constants
import com.example.ioasysmvvm.model.domains.user.EmailStatus
import com.example.ioasysmvvm.model.domains.user.User
import com.example.ioasysmvvm.model.extensions.createLoadingDialog
import com.example.ioasysmvvm.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var loginViewBinding: ActivityLoginBinding
    private var loadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginViewBinding.root)
        loadingDialog = createLoadingDialog ()
        setupObservers()
        setupLoginButton()
    }

    private fun setupLoginButton() {
       loginViewBinding.loginButton.setOnClickListener {
            val user = setupUser()
            loginViewModel.doLogin(user)
        }
    }

    private fun setupObservers() {
        loginViewModel.isValidUserEmail.observe(this, {
            treatInvalidEmail(it)
        })
        loginViewModel.isValidUserPassword.observe(this, {
            treatInvalidPassword(it)
        })
        loginViewModel.isAuthenticatedUser.observe(this, {
//            if (it) {
//                moveMainActivity(accessToken, uid, client)
//            }
        })
    }

    private fun moveMainActivity(accessToken: String, uid: String, client: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(Constants.HEADER_ACCESS_TOKEN, accessToken)
        intent.putExtra(Constants.HEADER_UID, uid)
        intent.putExtra(Constants.HEADER_CLIENT, client)
        startActivity(intent)
        finish()
    }


    private fun treatInvalidPassword(isValidPassword: Boolean) {
        if (isValidPassword) {
            loginViewBinding.loginPasswordTextInputLayout.error = Constants.EMPTY
        } else {
            loginViewBinding.loginPasswordTextInputLayout.error = getString(R.string.fill_the_field)
        }
    }

    private fun treatInvalidEmail(isValidEmail: EmailStatus) {
        when (isValidEmail) {
            EmailStatus.VALID -> {
                loginViewBinding.loginEmailTextInputLayout.error = Constants.EMPTY
            }
            EmailStatus.EMPTY -> {
                loginViewBinding.loginEmailTextInputLayout.error = getString(R.string.fill_the_field)
            }
            else -> {
                loginViewBinding.loginEmailTextInputLayout.error = getString(R.string.incorrect_email)
            }
        }
    }

    private fun setupUser() = User(
        loginViewBinding.loginEmailEditText.text?.toString().orEmpty(),
        loginViewBinding.loginPasswordEditText.text?.toString().orEmpty()
    )
}