package com.example.socioinfonavit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.socioinfonavit.databinding.ActivityLoginBinding
import com.example.socioinfonavit.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val loginViewModel = LoginViewModel(this)
        binding.vm = loginViewModel
        binding.lifecycleOwner = this
        binding.txtPassword.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                validateAndLogin(loginViewModel)
                true
            }else{
                false
            }
        }

        binding.btnLogin.setOnClickListener {
            validateAndLogin(loginViewModel)
        }
    }

    private fun validateAndLogin(viewModel: LoginViewModel) {
        if(viewModel.validateEmail())
            viewModel.login()
        else
            binding.txtUser.error = resources.getString(R.string.email_error)
    }
}