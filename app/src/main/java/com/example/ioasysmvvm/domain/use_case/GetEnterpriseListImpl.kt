package com.example.ioasysmvvm.domain.use_case

import com.example.ioasysmvvm.domain.exception.EmptyEnterpriseListException
import com.example.ioasysmvvm.domain.model.enterprise.Enterprise
import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.domain.repository.enterprise.EnterpriseRepository

class GetEnterpriseListImpl(private val enterpriseRepository: EnterpriseRepository) :
    GetEnterpriseList {
    override suspend fun call(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ): Result<List<Enterprise>> {
        val enterpriseList =
            enterpriseRepository.getEnterpriseList(enterpriseName, accessToken, client, uid)
        if (enterpriseList == emptyList<Result<List<Enterprise>>>()) {
            throw EmptyEnterpriseListException()
        }
        return enterpriseList
    }
}