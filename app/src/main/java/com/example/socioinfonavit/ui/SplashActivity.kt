package com.example.socioinfonavit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.socioinfonavit.R
import com.example.socioinfonavit.databinding.ActivityMainBinding
import com.example.socioinfonavit.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )

        val TOTAL_TIME : Long = 1000
        val INTERVAL_TIME : Long = 1000
        var showSplash = true
        object  : CountDownTimer(TOTAL_TIME, INTERVAL_TIME) {
            override fun onTick(millisUntilFinished: Long) {
                if(showSplash) {
                    binding.loader.visibility = View.VISIBLE
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        Glide.with(this@SplashActivity)
                            .load(resources.getDrawable(R.drawable.logo, theme))
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(binding.ivLogo)

                        showSplash = false
                    }
                }
            }

            override fun onFinish() {
                binding.loader.visibility = View.GONE
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

        }.start()
    }
}