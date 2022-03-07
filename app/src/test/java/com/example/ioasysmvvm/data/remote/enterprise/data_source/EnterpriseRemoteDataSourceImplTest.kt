package com.example.ioasysmvvm.data.remote.enterprise.data_source

import com.example.ioasysmvvm.data.api.EnterpriseService
import com.example.ioasysmvvm.data.remote.enterprise.model.EnterpriseListResponse
import com.example.ioasysmvvm.domain.exception.ServerErrorException
import com.example.ioasysmvvm.domain.model.result.Result
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
class EnterpriseRemoteDataSourceImplTest {
    private val service: EnterpriseService = mockk()
    private lateinit var enterpriseRemoteDataSourceImpl: EnterpriseRemoteDataSourceImpl

    @Before
    fun setupMocks() {
        enterpriseRemoteDataSourceImpl = EnterpriseRemoteDataSourceImpl(service)
    }

    @After
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN a call on getEnterpriseList THEN call service`() = runBlockingTest {
        val enterpriseName = "test"
        val accessToken = "41213e12e"
        val client = "teste@gmail.com"
        val uid = "2132e12e12"

        coEvery {
            service.getEnterpriseListResponse(any(), any(), any(), any())
        } returns mockk(relaxed = true)

        enterpriseRemoteDataSourceImpl.getEnterpriseList(
            enterpriseName, accessToken, client, uid
        )
        coVerify(exactly = 1) {
            service.getEnterpriseListResponse(
                enterpriseName, accessToken, client, uid
            )
        }
    }

    @Test
    fun `Given a call on getEnterpriseList WHEN has a error on service THEN returns error`() =
        runBlockingTest {
            val enterpriseName = "test"
            val accessToken = "41213e12e"
            val client = "teste@gmail.com"
            val uid = "2132e12e12"

            val exceptionExpected = HttpException(
                Response.error<HttpException>(400, mockk(relaxed = true))
            )

            coEvery {
                service.getEnterpriseListResponse(any(), any(), any(), any())
            } throws exceptionExpected

            val result = enterpriseRemoteDataSourceImpl.getEnterpriseList(
                enterpriseName, accessToken, client, uid
            )
            assertTrue(result is Result.Error && result.exception is ServerErrorException)
        }

    @Test
    fun `Given a call on getEnterpriseList WHEN has a success on service THEN return mapped response`() =
        runBlockingTest {
            val enterpriseName = "test"
            val accessToken = "41213e12e"
            val client = "teste@gmail.com"
            val uid = "2132e12e12"

            val enterpriseListResponse = mockk<EnterpriseListResponse>(relaxed = true)

            val expected = enterpriseListResponse.mapToEnterpriseListModel()

            coEvery {
                service.getEnterpriseListResponse(any(), any(), any(), any())
            } returns enterpriseListResponse

            val result = enterpriseRemoteDataSourceImpl.getEnterpriseList(
                enterpriseName, accessToken, client, uid
            ) as Result.Success

            assertEquals(expected, result.data)
        }
}