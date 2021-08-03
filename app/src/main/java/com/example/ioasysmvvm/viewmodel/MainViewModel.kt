package com.example.ioasysmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ioasysmvvm.model.domains.enterprise.Enterprise
import com.example.ioasysmvvm.model.domains.result.Result
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
    private val _informationToStartSearch = MutableLiveData<Boolean>()
    val informationToInit: LiveData<Boolean> = _informationToStartSearch

    fun getEnterpriseList(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ) {
        viewModelScope.launch(dispatcher) {
            _loading.postValue(true)
            _informationToStartSearch.postValue(false)
            val result = enterpriseRepository.getEnterpriseList(
                enterpriseName,
                accessToken,
                client,
                uid
            )
            when (result) {
                is Result.Error -> {
                    _loading.postValue(false)
                    _error.postValue(result.exception)
                }
                is Result.Success -> {
                    _loading.postValue(false)
                    _enterpriseList.postValue(result.data)
                }
            }
        }
    }
}