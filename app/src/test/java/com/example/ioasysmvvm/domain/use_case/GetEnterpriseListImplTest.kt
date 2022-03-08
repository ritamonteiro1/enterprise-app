package com.example.ioasysmvvm.domain.use_case

import com.example.ioasysmvvm.domain.repository.enterprise.EnterpriseRepository
import io.mockk.clearAllMocks
import io.mockk.mockk
import org.junit.After
import org.junit.Before

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
}