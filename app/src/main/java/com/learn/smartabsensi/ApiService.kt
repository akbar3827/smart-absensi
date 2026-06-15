package com.learn.smartabsensi

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("data.json")
    fun getCurrentData() : Call<Response>
}