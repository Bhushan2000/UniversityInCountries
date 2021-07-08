package com.example.universityincountries

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiService {


//      without coroutine
//    @GET("/search?country=india")
//    fun getUniversitiesData(): Call<List<University>>


    // with coroutine
    @GET("/search?country=india")
    suspend fun getUniversitiesData(): Response<List<University>>

    companion object {
        operator fun invoke(): ApiService {
            return Retrofit.Builder().baseUrl("http://universities.hipolabs.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)

        }
    }


}