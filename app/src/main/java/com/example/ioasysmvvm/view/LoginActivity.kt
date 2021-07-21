package com.example.ioasysmvvm.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.ioasysmvvm.R
import com.example.ioasysmvvm.model.constants.Constants
import com.example.ioasysmvvm.model.domains.user.EmailStatus
import com.example.ioasysmvvm.model.domains.user.User
import com.example.ioasysmvvm.model.domains.user.UserRequest
import com.example.ioasysmvvm.model.extensions.createLoadingDialog
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
    private var loadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loadingDialog = createLoadingDialog()
        findViewsById()
        setupLoginButton()
    }

    private fun setupLoginButton() {
        loginButton?.setOnClickListener {
            val user = setupUser()
            verifyValidUserEmailAndPassword(user)
        }
    }

    private fun verifyValidUserEmailAndPassword(user: User) {
        loginViewModel.isValidEmail(user)
        var isValidEmail: EmailStatus = EmailStatus.EMPTY
        loginViewModel.isValidUserEmail.observe(this, {
            isValidEmail = it
            treatInvalidEmail(isValidEmail)
        })
        loginViewModel.isValidPassword(user)
        var isValidPassword = false
        loginViewModel.isValidUserPassword.observe(this, {
            isValidPassword = it
            treatInvalidPassword(isValidPassword)
        })
        if (isValidEmail != EmailStatus.VALID || !isValidPassword) {
            return
        } else {
            loadingDialog?.show()
            val userRequest = UserRequest(user.email, user.password)
            loginViewModel.verifyAuthenticatedUser(userRequest)
            verifyAuthenticatedUser()
        }
    }

    private fun verifyAuthenticatedUser() {
        loginViewModel.isAuthenticatedUser.observe(this, {
            var isAuthenticatedUser = it
        })
    }

    private fun treatInvalidPassword(isValidPassword: Boolean) {
        if (isValidPassword) {
            loginPasswordTextInputLayout?.error = Constants.EMPTY
        } else {
            loginPasswordTextInputLayout?.error = getString(R.string.fill_the_field)
        }
    }

    private fun treatInvalidEmail(isValidEmail: EmailStatus) {
        when (isValidEmail) {
            EmailStatus.VALID -> {
                loginEmailTextInputLayout?.error = Constants.EMPTY
            }
            EmailStatus.EMPTY -> {
                loginEmailTextInputLayout?.error = getString(R.string.fill_the_field)
            }
            else -> {
                loginEmailTextInputLayout?.error = getString(R.string.incorrect_email)
            }
        }
    }

    private fun setupUser() = User(
        loginEmailEditText?.text?.toString().orEmpty(),
        loginPasswordEditText?.text?.toString().orEmpty()
    )

    private fun findViewsById() {
        loginEmailEditText = findViewById(R.id.loginEmailEditText)
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText)
        loginButton = findViewById(R.id.loginButton)
        loginEmailTextInputLayout = findViewById(R.id.loginEmailTextInputLayout)
        loginPasswordTextInputLayout = findViewById(R.id.loginPasswordTextInputLayout)
    }
}