package com.example.registercandidate.network

import okhttp3.Interceptor
import okhttp3.Response


class CustomInterceptor(private val apiKey: String?, private val mobileNumber: String?, ) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val builder = originalRequest.newBuilder()
        apiKey?.let { builder.addHeader("X-ApiKey", it) } // Add API key
        mobileNumber?.let { builder.addHeader("X-MobNo", it) }  // Add mobile number
        val newRequest = builder.build()
        return chain.proceed(newRequest)
    }
}