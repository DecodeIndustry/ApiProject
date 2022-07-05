package com.example.apiproject

import com.example.apiproject.models.HadisItemModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("get_hadies.php")
    fun getHadis():Call<List<HadisItemModel>>

}