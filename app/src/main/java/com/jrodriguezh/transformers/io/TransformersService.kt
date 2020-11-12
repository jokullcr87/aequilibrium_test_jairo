package com.jrodriguezh.transformers.io

import com.jrodriguezh.transformers.data.Transformer
import com.jrodriguezh.transformers.data.TransformersList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface TransformersService {

    @GET("transformers")
    suspend fun transformers(@Header("authorization") auth: String): Response<TransformersList>

    @POST("transformers")
    suspend fun saveTransformer(@Header("authorization") auth: String,
                                @Body transformer: Transformer): Response<Transformer>

    @PUT("transformers")
    suspend fun updateTransformer(@Header("authorization") auth: String,
                                  @Body transformer: Transformer): Response<Transformer>

    @DELETE("transformers/{id}/")
    suspend fun deleteTransformer(@Header("authorization") auth: String,
                                  @Path("id") transformerId: String): Response<Void>

}