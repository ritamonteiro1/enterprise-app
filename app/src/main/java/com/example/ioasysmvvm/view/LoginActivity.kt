package com.example.ioasysmvvm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ioasysmvvm.R
import com.example.ioasysmvvm.databinding.ActivityLoginBinding
import com.example.ioasysmvvm.model.constants.Constants
import com.example.ioasysmvvm.model.domains.errors.LoginError
import com.example.ioasysmvvm.model.domains.user.EmailStatus
import com.example.ioasysmvvm.model.domains.user.User
import com.example.ioasysmvvm.model.extensions.createLoadingDialog
import com.example.ioasysmvvm.model.extensions.showErrorDialog
import com.example.ioasysmvvm.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding
    private val loadingDialog by lazy { createLoadingDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        setupLoginButton()
    }

    private fun setupLoginButton() {
        binding.loginButton.setOnClickListener {
            val user = setupUser()
            viewModel.doLogin(user)
        }
    }

    private fun setupObservers() {
        viewModel.isValidUserEmail.observe(this, {
            treatInvalidEmail(it)
        })
        viewModel.isValidUserPassword.observe(this, {
            treatInvalidPassword(it)
        })
        viewModel.loading.observe(this) {
            if (it) loadingDialog.show()
            else loadingDialog.dismiss()
        }
        viewModel.error.observe(this) {
            when (it) {
                is LoginError -> unauthorizedLogin()
                else -> showErrorDialog(it.message.orEmpty())
            }
        }
        viewModel.userTokens.observe(this){
            moveToMainActivity(it.accessToken, it.uid, it.client)
        }
    }

    private fun unauthorizedLogin() {
        binding.loginEmailTextInputLayout.error = Constants.BLANK_SPACE
        binding.loginPasswordTextInputLayout.error =
            getString(R.string.login_error_email_password)
    }

    private fun moveToMainActivity(accessToken: String, uid: String, client: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(Constants.HEADER_ACCESS_TOKEN, accessToken)
        intent.putExtra(Constants.HEADER_UID, uid)
        intent.putExtra(Constants.HEADER_CLIENT, client)
        startActivity(intent)
        finish()
    }

    private fun treatInvalidPassword(isValidPassword: Boolean) {
        if (isValidPassword) {
            binding.loginPasswordTextInputLayout.error = Constants.EMPTY
        } else {
            binding.loginPasswordTextInputLayout.error = getString(R.string.fill_the_field)
        }
    }

    private fun treatInvalidEmail(isValidEmail: EmailStatus) {
        when (isValidEmail) {
            EmailStatus.VALID -> {
                binding.loginEmailTextInputLayout.error = Constants.EMPTY
            }
            EmailStatus.EMPTY -> {
                binding.loginEmailTextInputLayout.error =
                    getString(R.string.fill_the_field)
            }
            else -> {
                binding.loginEmailTextInputLayout.error =
                    getString(R.string.incorrect_email)
            }
        }
    }

    private fun setupUser() = User(
        binding.loginEmailEditText.text?.toString().orEmpty(),
        binding.loginPasswordEditText.text?.toString().orEmpty()
    )
}