package com.example.ioasysmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ioasysmvvm.model.domains.user.UserTokens
import com.example.ioasysmvvm.model.domains.user.EmailStatus
import com.example.ioasysmvvm.model.domains.user.User
import com.example.ioasysmvvm.model.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlin.coroutines.CoroutineContext
import com.example.ioasysmvvm.model.domains.result.Result


class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : ViewModel() {
    private val _isValidUserEmail = MutableLiveData<EmailStatus>()
    val isValidUserEmail: LiveData<EmailStatus> = _isValidUserEmail
    private val _isValidUserPassword = MutableLiveData<Boolean>()
    val isValidUserPassword: LiveData<Boolean> = _isValidUserPassword
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _userTokens = MutableLiveData<UserTokens>()
    val userTokens: LiveData<UserTokens> = _userTokens
    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    fun doLogin(user: User) {
        viewModelScope.launch(dispatcher) {
            isValidEmail(user)
            isValidPassword(user)
            if (isValidEmail(user) == EmailStatus.VALID && isValidPassword(user)) {
                _loading.postValue(true)
                when (val result = loginRepository.doLogin(user)) {
                    is Result.Error -> {
                        _loading.postValue(false)
                        _error.postValue(result.exception)
                    }
                    is Result.Success -> {
                        _loading.postValue(false)
                        _userTokens.postValue(result.data)
                    }
                }
            } else {
                _loading.postValue(false)
            }
        }
    }

    private fun isValidEmail(user: User): EmailStatus {
        val isValidEmail: EmailStatus = user.isValidEmail()
        _isValidUserEmail.postValue(isValidEmail)
        return isValidEmail
    }

    private fun isValidPassword(user: User): Boolean {
        val isValidPassword: Boolean = user.isValidPassword()
        _isValidUserPassword.postValue(isValidPassword)
        return isValidPassword
    }
}