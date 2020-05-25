package com.das.bd.android_mvvm_kotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.das.bd.android_mvvm_kotlin.data.network.ApiClicnt
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    fun userLogin(email: String, password: String) : LiveData<String> {

        val loginResponse = MutableLiveData<String>()

        ApiClicnt().userLogin(email, password)
            .enqueue(object: Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if(response.isSuccessful){
                        loginResponse.value = response.body()?.string()
                    }else{
                        loginResponse.value = response.errorBody()?.string()
                    }
                }

            })

        return loginResponse
    }


}





