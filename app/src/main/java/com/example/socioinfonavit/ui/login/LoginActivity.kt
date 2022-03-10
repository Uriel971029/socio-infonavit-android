package com.example.socioinfonavit.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.databinding.DataBindingUtil
import com.example.socioinfonavit.R
import com.example.socioinfonavit.databinding.ActivityLoginBinding
import com.example.socioinfonavit.ui.login.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val loginViewModel = LoginViewModel(this)
        binding.vm = loginViewModel
        binding.lifecycleOwner = this
        binding.txtPassword.setOnKeyListener { _, keyCode, event ->
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