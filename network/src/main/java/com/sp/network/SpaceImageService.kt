package com.sp.network

import com.sp.common.entities.SearchResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SpaceImageService {

    @GET("search?media_type=image")
    suspend fun getImages(
        @Query(value = "q") searchTerm: String,
        @Query(value = "page") page: Int,
    ): SearchResponse

    companion object {
        var retrofitService: SpaceImageService? = null
        fun getInstance(): SpaceImageService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://images-api.nasa.gov/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(SpaceImageService::class.java)
            }
            return retrofitService!!
        }

    }
}