package com.example.registercandidate.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.registercandidate.HomeActivity
import com.example.registercandidate.RegisterCandidate
import com.example.registercandidate.network.ApiServiceInterface
import com.example.votersearch.R
import com.example.votersearch.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private var mApiService: ApiServiceInterface?= null
    private var cocAllocated:String = "false"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load the animation
        val logoAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_logo_animation)
        binding.splashLogo.startAnimation(logoAnimation)

        // Delay and start the main activity
        Handler(Looper.getMainLooper()).postDelayed({
          //  val intent = Intent(this, LoginActivity::class.java)
          //  startActivity(intent)
          navigateActivity()// Close the splash activity so the user can't go back
        }, 3000) // Delay for 3 seconds (adjust as needed)
    }

    fun navigateActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}