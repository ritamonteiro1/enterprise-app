package com.example.ioasysmvvm.presentation.enterprise.enterprise_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ioasysmvvm.domain.model.enterprise.Enterprise
import com.example.ioasysmvvm.domain.model.result.Result
import com.example.ioasysmvvm.domain.use_case.GetEnterpriseList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class EnterpriseListViewModel(
    private val getEnterpriseList: GetEnterpriseList,
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
            val result = getEnterpriseList.call(
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