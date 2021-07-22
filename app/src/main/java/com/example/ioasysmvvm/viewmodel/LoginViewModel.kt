package com.example.ioasysmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ioasysmvvm.model.domains.user.EmailStatus
import com.example.ioasysmvvm.model.domains.user.User
import com.example.ioasysmvvm.model.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlin.coroutines.CoroutineContext


class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : ViewModel() {
    private val _isAuthenticatedUser = MutableLiveData<Boolean>()
    val isAuthenticatedUser: LiveData<Boolean> = _isAuthenticatedUser
    private val _isValidUserEmail = MutableLiveData<EmailStatus>()
    val isValidUserEmail: LiveData<EmailStatus> = _isValidUserEmail
    private val _isValidUserPassword = MutableLiveData<Boolean>()
    val isValidUserPassword: LiveData<Boolean> = _isValidUserPassword

    fun doLogin(user: User) {
        viewModelScope.launch(dispatcher) {
            isValidEmail(user)
            isValidPassword(user)
            if (isValidEmail(user) == EmailStatus.VALID && isValidPassword(user)) {
                val isLoginAuthenticatedUser = loginRepository.isAuthenticatedUser(user)
                _isAuthenticatedUser.postValue(isLoginAuthenticatedUser)
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