package com.example.ioasysmvvm.model.repository

import com.example.ioasysmvvm.model.domains.enterprise.EnterpriseList

interface MainRepository {
    suspend fun getEnterpriseList(): EnterpriseList
}