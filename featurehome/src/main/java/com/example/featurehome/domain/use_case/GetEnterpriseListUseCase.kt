package com.example.featurehome.domain.use_case

import com.example.datasource.model.enterprise.Enterprise
import com.example.datasource.model.exceptions.EmptyEnterpriseListException
import com.example.datasource.repository.enterprise.EnterpriseRepository
import com.example.datasource.result.Result

interface GetEnterpriseListUseCase {
    suspend fun call(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ): Result<List<Enterprise>>
}

class GetEnterpriseListUseCaseImpl(private val enterpriseRepository: EnterpriseRepository) :
    GetEnterpriseListUseCase {
    override suspend fun call(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ): Result<List<Enterprise>> {
        val result =
            enterpriseRepository.getEnterpriseList(enterpriseName, accessToken, client, uid)
        return if (result is Result.Success && result.data.isEmpty()) {
            Result.Error(EmptyEnterpriseListException())
        } else {
            result
        }
    }
}
