package com.example.ioasysmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ioasysmvvm.model.domains.enterprise.Enterprise
import com.example.ioasysmvvm.model.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel(
    private val mainRepository: MainRepository,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : ViewModel() {
    private val _enterpriseList = MutableLiveData<List<Enterprise>>()
    val enterpriseList: LiveData<List<Enterprise>> = _enterpriseList

    fun getEnterpriseList(
        accessToken: String,
        client: String,
        uid: String,
        enterpriseName: String
    ) {
        viewModelScope.launch(dispatcher) {
//            val enterprises: List<Enterprise> =
//                mainRepository.getEnterpriseList(accessToken, client, uid, enterpriseName)
//            _enterpriseList.postValue(enterprises)
        }
    }
}