package com.jrodriguezh.transformers.io

import retrofit2.Response
import retrofit2.http.GET

interface AllSparkService {

    @GET("allspark")
    suspend fun allSpark(): Response<String>

}