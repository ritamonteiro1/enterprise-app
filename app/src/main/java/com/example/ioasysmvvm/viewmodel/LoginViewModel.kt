package com.example.ioasysmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ioasysmvvm.model.domains.user.EmailStatus
import com.example.ioasysmvvm.model.domains.user.User
import com.example.ioasysmvvm.model.domains.user.UserRequest
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
    private val _isUserValidEmail = MutableLiveData<EmailStatus>()
    val isUserValidEmail: LiveData<EmailStatus> = _isUserValidEmail
    private val _isUserValidPassword = MutableLiveData<Boolean>()
    val isUserValidPassword: LiveData<Boolean> = _isUserValidPassword

    fun verifyAuthenticatedUser(userRequest: UserRequest) {
        viewModelScope.launch(dispatcher) {
            val isLoginAuthenticatedUser = loginRepository.isAuthenticatedUser(userRequest)
            _isAuthenticatedUser.postValue(isLoginAuthenticatedUser)
        }
    }

    fun isValidEmail(user: User) {
        val isValidEmail: EmailStatus = user.isValidEmail()
        _isUserValidEmail.postValue(isValidEmail)
    }

    fun isValidPassword(user: User) {
        val isValidPassword: Boolean = user.isValidPassword()
        _isUserValidPassword.postValue(isValidPassword)
    }
}