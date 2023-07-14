package com.test.Suitmedia.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.Suitmedia.model.Data
import com.test.Suitmedia.model.DataX
import com.test.Suitmedia.model.SelectedUser
import com.test.Suitmedia.model.UserData
import com.test.Suitmedia.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel: ViewModel() {
    var liveDataUser:MutableLiveData<List<Data>>
    var liveSelectedUser: MutableLiveData<DataX>
    init {
        liveDataUser = MutableLiveData()
        liveSelectedUser = MutableLiveData()
    }

    fun getUsers(){
        RetrofitClient.instance.getUsers(
            page = 1,
            per_page = 10
        ).enqueue(object : Callback<UserData>{
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                if(response.isSuccessful){
                    val userResponse = response.body()
                    liveDataUser.postValue(userResponse!!.data)
                }else{
                    Log.d("info", response.body().toString())
                }
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {
                Log.d("Failed", t.message.toString())
            }

        })
    }
    fun getSelecetedUser(userId: Int){
        RetrofitClient.instance.getSelectedUsers(userId).enqueue(object : Callback<SelectedUser>{
            override fun onResponse(call: Call<SelectedUser>, response: Response<SelectedUser>) {
                if (response.isSuccessful) {
                    val selectedResponse = response.body()
                    liveSelectedUser.postValue(selectedResponse!!.data)
                    Log.d("info", response.body().toString())
                }else{
                    Log.e("info", response.body().toString())
                }

            }

            override fun onFailure(call: Call<SelectedUser>, t: Throwable) {
                Log.d("Failed", t.message.toString())
            }

        })
    }
}