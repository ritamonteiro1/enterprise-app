package com.example.ioasysmvvm.model.repository

import com.example.ioasysmvvm.model.domains.enterprise.Enterprise

interface MainRepository {
    suspend fun getEnterpriseList(): List<Enterprise>
}