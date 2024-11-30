package com.example.mobileappeducation.data


import com.example.mobileappeducation.model.Books
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("recent")
    suspend fun getBooksList(): Response<Books>
}