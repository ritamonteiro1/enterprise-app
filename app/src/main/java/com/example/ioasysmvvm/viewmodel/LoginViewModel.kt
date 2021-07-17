package com.example.ioasysmvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ioasysmvvm.model.api.Api
import com.example.ioasysmvvm.model.api.DataService
import com.example.ioasysmvvm.model.domains.user.UserRequest
import okhttp3.ResponseBody
import retrofit2.Call


class LoginViewModel : ViewModel() {
    fun initLoginViewModel(userRequest: UserRequest) {
        val dataService: DataService = Api.setupRetrofit().create(DataService::class.java)
        val call: Call<ResponseBody> = dataService.recoverVerifyLogin(userRequest)
        doLogin(call)
    }
    private fun doLogin(call: Call<ResponseBody>) {

    }
}