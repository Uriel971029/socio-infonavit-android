package com.example.socioinfonavit

import android.content.Intent
import android.graphics.drawable.TransitionDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.socioinfonavit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val TOTAL_TIME : Long = 1000
        val INTERVAL_TIME : Long = 1000
        var showSplash = true
        object  : CountDownTimer(TOTAL_TIME, INTERVAL_TIME) {
            override fun onTick(millisUntilFinished: Long) {
                if(showSplash) {
                    binding.loader.visibility = View.VISIBLE
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        Glide.with(this@MainActivity)
                            .load(resources.getDrawable(R.drawable.logo, theme))
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(binding.ivLogo)

                        showSplash = false
                    }
                }
            }

            override fun onFinish() {
                binding.loader.visibility = View.GONE
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

        }.start()
    }
}