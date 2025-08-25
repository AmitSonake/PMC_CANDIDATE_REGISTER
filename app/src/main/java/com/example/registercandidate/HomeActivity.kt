package com.example.registercandidate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.registercandidate.network.ApiClient
import com.example.registercandidate.network.ApiServiceInterface
import com.example.registercandidate.utilities.CheckInternetConnection
import com.example.votersearch.databinding.ActivityHomeBinding
import com.example.votersearch.databinding.ActivityRegisterCandidateBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var mApiService: ApiServiceInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val intent = Intent(this, RegisterCandidate::class.java)
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this, CandidateListActivity::class.java)
            startActivity(intent)
        }
    }


}