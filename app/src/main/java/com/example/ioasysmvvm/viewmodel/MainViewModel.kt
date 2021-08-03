package com.example.ioasysmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ioasysmvvm.model.domains.enterprise.Enterprise
import com.example.ioasysmvvm.model.repository.EnterpriseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel(
    private val enterpriseRepository: EnterpriseRepository,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : ViewModel() {
    private val _enterpriseList = MutableLiveData<List<Enterprise>>()
    val enterpriseList: LiveData<List<Enterprise>> = _enterpriseList
    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getEnterpriseList(
        accessToken: String,
        client: String,
        uid: String,
        enterpriseName: String
    ) {
        viewModelScope.launch(dispatcher) {
            val result = enterpriseRepository.getEnterpriseList(accessToken, client, uid, enterpriseName)
        }
    }
}