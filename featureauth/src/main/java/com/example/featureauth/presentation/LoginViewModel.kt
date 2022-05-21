package com.example.featureauth.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datasource.model.UserTokens
import com.example.featureauth.domain.model.EmailStatus
import com.example.featureauth.domain.model.PasswordStatus
import com.example.featureauth.domain.use_case.DoLoginUseCase
import com.example.featureauth.domain.use_case.ValidateUserEmailUseCase
import com.example.featureauth.domain.use_case.ValidateUserPasswordUseCase
import com.example.datasource.model.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import com.example.datasource.result.Result

class LoginViewModel(
    private val validateUserEmail: ValidateUserEmailUseCase,
    private val validateUserPassword: ValidateUserPasswordUseCase,
    private val doLoginUseCase: DoLoginUseCase,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : ViewModel() {
    private val _isValidUserEmail = MutableLiveData<EmailStatus>()
    val isValidUserEmail: LiveData<EmailStatus> = _isValidUserEmail
    private val _isValidUserPassword = MutableLiveData<PasswordStatus>()
    val isValidUserPassword: LiveData<PasswordStatus> = _isValidUserPassword
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _userTokens = MutableLiveData<UserTokens>()
    val userTokens: LiveData<UserTokens> = _userTokens
    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    fun doLogin(email: String, password: String) {
        viewModelScope.launch(dispatcher) {
            val isValidEmail = validateUserEmail(email)
            val isValidPassword = validateUserPassword(password)
            if (isValidEmail == EmailStatus.VALID && isValidPassword == PasswordStatus.VALID) {
                val typedUser = User(email, password)
                _loading.postValue(true)
                when (val result = doLoginUseCase.call(typedUser)) {
                    is Result.Error -> {
                        _loading.postValue(false)
                        _error.postValue(result.exception)
                    }
                    is Result.Success -> {
                        _loading.postValue(false)
                        _userTokens.postValue(result.data!!)
                    }
                }
            }
        }
    }

    private fun validateUserEmail(email: String): EmailStatus {
        val isValidEmail = validateUserEmail.call(email)
        _isValidUserEmail.postValue(isValidEmail)
        return isValidEmail
    }

    private fun validateUserPassword(password: String): PasswordStatus {
        val isValidPassword = validateUserPassword.call(password)
        _isValidUserPassword.postValue(isValidPassword)
        return isValidPassword
    }
}