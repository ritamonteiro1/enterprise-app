package com.example.ioasysmvvm.data.remote.login.data_source

import com.example.ioasysmvvm.data.api.LoginService
import com.example.ioasysmvvm.data.remote.login.model.UserRequest
import com.example.ioasysmvvm.domain.exception.ServerErrorException
import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.domain.model.user.User
import com.example.ioasysmvvm.domain.model.user.UserTokens
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

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

    @Test
    fun `GIVEN a call on doLogin THEN call service`() = runBlockingTest {
        val user = User("testeapple@ioasys.com.br", "12341234")

        coEvery {
            service.doLogin(any())
        } returns mockk(relaxed = true)

        loginRemoteDataSourceImpl.doLogin(
            user
        )

        coVerify(exactly = 1) {
            service.doLogin(UserRequest(user.email, user.password))
        }
    }

    @Test
    fun `GIVEN a call on doLogin WHEN has a error on service THEN returns error`() =
        runBlockingTest {
            val user = User("testeapple@ioasys.com.br", "12341234")

            val exceptionExpected = HttpException(
                Response.error<HttpException>(400, mockk(relaxed = true))
            )

            coEvery {
                service.doLogin(any())
            } throws exceptionExpected

            val result = loginRemoteDataSourceImpl.doLogin(
                user
            )
            Assert.assertTrue(result is Result.Error && result.exception is ServerErrorException)
        }
}