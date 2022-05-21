package com.example.featurehome.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datasource.model.enterprise.Enterprise
import com.example.datasource.result.Result
import com.example.featurehome.domain.use_case.GetEnterpriseListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class EnterpriseListViewModel(
    private val getEnterpriseList: GetEnterpriseListUseCase,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : ViewModel() {
    private val _enterpriseList = MutableLiveData<List<Enterprise>>()
    val enterpriseList: LiveData<List<Enterprise>> = _enterpriseList
    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getEnterpriseList(
        enterpriseName: String,
        accessToken: String,
        client: String,
        uid: String
    ) {
        viewModelScope.launch(dispatcher) {
            _loading.postValue(true)
            when (val result = getEnterpriseList.call(enterpriseName, accessToken, client, uid)) {
                is Result.Error -> {
                    _loading.postValue(false)
                    _error.postValue(result.exception)
                }
                is Result.Success -> {
                    _loading.postValue(false)
                    _enterpriseList.postValue(result.data!!)
                }
            }
        }
    }
}