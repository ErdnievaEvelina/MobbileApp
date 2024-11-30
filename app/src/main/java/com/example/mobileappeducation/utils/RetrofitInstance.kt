package com.example.mobileappeducation.utils

import com.example.mobileappeducation.data.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api:ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(Util.BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}