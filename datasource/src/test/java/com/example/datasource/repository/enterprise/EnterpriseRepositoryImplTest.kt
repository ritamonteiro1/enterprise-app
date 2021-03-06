package com.example.datasource.repository.enterprise

import com.example.datasource.model.enterprise.Enterprise
import com.example.datasource.result.Result
import com.example.datasource.remote.enterprise.EnterpriseRemoteDataSource
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
    fun `GIVEN a call on getEnterpriseList WHEN request is fail THEN it should returns a Error Response`() =
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


    @Test
    fun `GIVEN a call on getEnterpriseList WHEN request is successfully and Enterprise List is not empty THEN it should returns a Success Response`() =
        runBlockingTest {
            val enterpriseName = "test"
            val accessToken = "41213e12e"
            val client = "teste@gmail.com"
            val uid = "2132e12e12"

            val expectedSuccess: Result.Success<List<Enterprise>> = mockk(relaxed = true)

            coEvery {
                enterpriseRemoteDataSource.getEnterpriseList(any(), any(), any(), any())
            } returns expectedSuccess

            enterpriseRepositoryImpl.getEnterpriseList(
                enterpriseName, accessToken, client, uid
            )
            val result =
                enterpriseRemoteDataSource.getEnterpriseList(
                    enterpriseName, accessToken, client, uid
                )

            Assert.assertEquals(expectedSuccess, result)
        }
}