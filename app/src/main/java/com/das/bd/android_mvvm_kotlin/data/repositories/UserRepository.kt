package com.das.bd.android_mvvm_kotlin.data.repositories

import com.das.bd.android_mvvm_kotlin.data.network.ApiClicnt
import com.das.bd.android_mvvm_kotlin.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository {
   suspend fun userLogin(email: String, password: String) : Response<AuthResponse> {
        return ApiClicnt().userLogin(email , password)

       /* val loginResponse = MutableLiveData<String>()

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

        return loginResponse*/
    }


}





