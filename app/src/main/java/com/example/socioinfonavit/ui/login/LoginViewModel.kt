package com.example.socioinfonavit.ui.login

import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.socioinfonavit.ui.home.HomeActivity
import com.example.socioinfonavit.R
import com.example.socioinfonavit.api.ApiService
import com.example.socioinfonavit.data.local.User
import com.example.socioinfonavit.data.remote.request.UserRequest
import com.example.socioinfonavit.data.remote.response.UserResponse
import com.example.socioinfonavit.utils.showGenericAlert
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LoginViewModel(val context: Context) : BaseObservable() {

    val user = User("prueba@nextia.mx", "PruebaNextia2021")

    @Bindable
    fun getEmail() : String {
        return user.email
    }

    fun setEmail(value: String){
        if(user.email != value){
            user.email = value
            validateButton()
            notifyPropertyChanged(BR.email)
        }
    }

    @Bindable
    fun getPassword() : String {
        return user.password
    }

    fun setPassword(value: String){
        if(user.password != value){
            user.password = value
            validateButton()
            notifyPropertyChanged(BR.password)
        }
    }

    private var buttonEnable : Boolean = false
    @Bindable
    fun getButtonEnable() : Boolean {
        return buttonEnable
    }

    private fun setButtonEnable(value: Boolean){
        if(buttonEnable != value){
            buttonEnable = value
            notifyPropertyChanged(BR.buttonEnable)
        }
    }

    private var showLoader : Boolean = false
    @Bindable
    fun getShowLoader() : Boolean {
        return showLoader
    }

    fun setShowLoader(value: Boolean){
        if(showLoader != value){
            showLoader = value
            notifyPropertyChanged(BR.showLoader)
        }
    }

    private fun validateButton() {
        var isButtonEnabled = user.email.isNotEmpty() && user.password.isNotEmpty()
            setButtonEnable(isButtonEnabled)
    }

    fun validateEmail() : Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(user.email).matches()
    }

    fun login() {

        val userData = UserRequest(User(user.email, user.password))
        val call = ApiService.postMethods?.login(userData)

        try {
            setShowLoader(true)
            call?.enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    Log.d("success_login", response.toString())
                    setShowLoader(false)
                    if(response.isSuccessful) {
                        response.headers()[ApiService.AUTH_HEADER].let {
                            ApiService.destroyClient()
                            ApiService.jwt = it!!
                            context.startActivity(Intent(context, HomeActivity::class.java))
                        }
                    } else if(response.code() == 401) {
                        Toast.makeText(context, context.resources.getString(R.string.credentials_error), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    setShowLoader(false)
                    showGenericAlert(context, 500)
                    Log.d("error_login", t.message.toString())
                }

            })
        }catch (ex : Exception){
            Toast.makeText(context, context.resources.getString(R.string.network_error), Toast.LENGTH_SHORT).show()
        }
    }

}