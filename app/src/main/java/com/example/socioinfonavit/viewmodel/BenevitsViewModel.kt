package com.example.socioinfonavit.viewmodel

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.socioinfonavit.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BenevitsViewModel(val contex: Context) : ViewModel() {

    fun logout() {
        val call = ApiService.postMethods?.logout()
        call?.enqueue(object: Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if(response.isSuccessful) {
                    ApiService.destroyClient()
                    var activity = contex as Activity
                    activity.finish()
                }else{
                    Toast.makeText(contex, "Error al cerrar la sesión", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(contex, "Error al cerrar la sesión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    class BenevitsViewModelFactory(val contex: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(BenevitsViewModel::class.java))
                return BenevitsViewModel(contex) as T
            else
                throw  IllegalArgumentException("cannot create BenevitsViewModel")
        }

    }
}