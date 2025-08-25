package com.example.registercandidate.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {
    val baseUrl : String?  = "http://api.aoinfotech.com/"
    private var retrofit : Retrofit? = null;
    val weatherUrl : String?  ="https://api.weatherapi.com/"
    private const val APP_KEY = "552556497339462MH16BZbr2024"

    fun getClient():Retrofit? {
        if(retrofit==null){

            val client =OkHttpClient.Builder()
                .connectTimeout(100,TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS)
                .build()
            retrofit =Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
       return retrofit
    }

    fun getClientRequestWithHeader(mobileNumber: String?= "",): Retrofit? {
        val client = OkHttpClient.Builder()
            .addInterceptor(CustomInterceptor(APP_KEY,"8830228583")) // Use custom interceptor
            .connectTimeout(100,TimeUnit.SECONDS)
            .readTimeout(100,TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getClientRequest(token:String? = ""):Retrofit? {
       val client =  OkHttpClient.Builder()
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return  retrofit
    }


    fun getSignUpClient():Retrofit? {
        if(retrofit==null){
            val client =OkHttpClient.Builder()
                .connectTimeout(100,TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS)
                .build()
            retrofit =Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

}