package com.example.ioasysmvvm.data.remote.login.data_source

import com.example.ioasysmvvm.data.api.LoginService
import io.mockk.clearAllMocks
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before

@ExperimentalCoroutinesApi
class LoginRemoteDataSourceImplTest {
    private val service: LoginService = mockk()
    private lateinit var loginRemoteDataSourceImpl: LoginRemoteDataSourceImpl

    @Before
    fun setupMocks() {
        loginRemoteDataSourceImpl = LoginRemoteDataSourceImpl(service)
    }

    @After
    fun resetMocks() {
        clearAllMocks()
    }
}