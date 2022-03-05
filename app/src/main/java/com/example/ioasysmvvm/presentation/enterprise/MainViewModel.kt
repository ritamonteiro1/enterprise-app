package com.example.ioasysmvvm.presentation.enterprise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ioasysmvvm.data.repository.EnterpriseRepository
import com.example.ioasysmvvm.domain.enterprise.Enterprise
import com.example.ioasysmvvm.domain.result.Result
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
    private val _emptyListMessage = MutableLiveData<Boolean>()
    val emptyListMessage: LiveData<Boolean> = _emptyListMessage

    fun getEnterpriseList(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ) {
        viewModelScope.launch(dispatcher) {
            _emptyListMessage.postValue(enterpriseName.isBlank())
            _loading.postValue(true)
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