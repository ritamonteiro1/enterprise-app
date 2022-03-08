package com.example.ioasysmvvm.data.repository.enterprise

import com.example.ioasysmvvm.data.remote.enterprise.data_source.EnterpriseRemoteDataSource
import com.example.ioasysmvvm.domain.model.result.Result
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class EnterpriseRepositoryImplTest {
    private val enterpriseRemoteDataSource: EnterpriseRemoteDataSource = mockk()
    private lateinit var enterpriseRepositoryImpl: EnterpriseRepositoryImpl

    @Before
    fun setupMocks() {
        enterpriseRepositoryImpl = EnterpriseRepositoryImpl(enterpriseRemoteDataSource)
    }

    @After
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN a call on getEnterpriseList THEN call enterpriseRemoteDataSource`() =
        runBlockingTest {
            val enterpriseName = "test"
            val accessToken = "41213e12e"
            val client = "teste@gmail.com"
            val uid = "2132e12e12"

            coEvery {
                enterpriseRemoteDataSource.getEnterpriseList(any(), any(), any(), any())
            } returns mockk(relaxed = true)

            enterpriseRepositoryImpl.getEnterpriseList(
                enterpriseName, accessToken, client, uid
            )
            coVerify(exactly = 1) {
                enterpriseRemoteDataSource.getEnterpriseList(
                    enterpriseName, accessToken, client, uid
                )
            }
        }

    @Test
    fun `GIVEN a call on getEnterpriseList WHEN request is fail THEN it should throw an Exception`() =
        runBlockingTest {
            val enterpriseName = "test"
            val accessToken = "41213e12e"
            val client = "teste@gmail.com"
            val uid = "2132e12e12"

            val expectedError: Result.Error = mockk(relaxed = true)


            coEvery {
                enterpriseRemoteDataSource.getEnterpriseList(any(), any(), any(), any())
            } returns expectedError

            enterpriseRepositoryImpl.getEnterpriseList(
                enterpriseName, accessToken, client, uid
            )
            val result =
                enterpriseRemoteDataSource.getEnterpriseList(
                    enterpriseName, accessToken, client, uid
                )

            Assert.assertEquals(expectedError, result)
        }
}