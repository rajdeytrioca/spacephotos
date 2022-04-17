package com.sp.network

import com.sp.common.entities.SearchResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceImageService {

    @GET("search")
    suspend fun getImages(
        @Query(value = "q") searchTerm: String,
        @Query(value = "media_type") type: String = "image"
    ): Response<List<SearchResponse>>

    companion object {
        var retrofitService: SpaceImageService? = null
        fun getInstance(): SpaceImageService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://images-assets.nasa.gov/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(SpaceImageService::class.java)
            }
            return retrofitService!!
        }

    }
}