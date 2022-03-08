package com.example.ioasysmvvm.domain.use_case

import com.example.ioasysmvvm.domain.model.enterprise.Enterprise
import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.domain.repository.enterprise.EnterpriseRepository
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
class GetEnterpriseListImplTest {
    private val enterpriseRepository: EnterpriseRepository = mockk()
    private lateinit var getEnterpriseListImpl: GetEnterpriseListImpl

    @Before
    fun setupMocks() {
        getEnterpriseListImpl = GetEnterpriseListImpl(enterpriseRepository)
    }

    @After
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN a call THEN call enterpriseRepository`() =
        runBlockingTest {
            val enterpriseName = "test"
            val accessToken = "41213e12e"
            val client = "teste@gmail.com"
            val uid = "2132e12e12"

            coEvery {
                enterpriseRepository.getEnterpriseList(any(), any(), any(), any())
            } returns mockk(relaxed = true)

            getEnterpriseListImpl.call(
                enterpriseName, accessToken, client, uid
            )
            coVerify(exactly = 1) {
                enterpriseRepository.getEnterpriseList(
                    enterpriseName, accessToken, client, uid
                )
            }
        }
    @Test
    fun `GIVEN a call WHEN request is fail THEN it should returns a Result Error`() =
        runBlockingTest {
            val enterpriseName = "test"
            val accessToken = "41213e12e"
            val client = "teste@gmail.com"
            val uid = "2132e12e12"

            val expectedError: Result.Error = mockk(relaxed = true)

            coEvery {
                enterpriseRepository.getEnterpriseList(any(), any(), any(), any())
            } returns expectedError

            getEnterpriseListImpl.call(
                enterpriseName, accessToken, client, uid
            )
            val result =
                enterpriseRepository.getEnterpriseList(
                    enterpriseName, accessToken, client, uid
                )

            Assert.assertEquals(expectedError, result)
        }
}