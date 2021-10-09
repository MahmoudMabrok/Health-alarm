package com.example.healthalarm.activites

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.healthalarm.R
import com.example.healthalarm.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySplashScreenBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        val animation = AnimationUtils.loadAnimation(this, R.anim.splashtransition)

        binding.splashImage.startAnimation(animation)
        lifecycleScope.launch {
            delay(1200)
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}