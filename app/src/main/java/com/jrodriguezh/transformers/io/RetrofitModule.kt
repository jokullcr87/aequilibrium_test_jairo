package com.jrodriguezh.transformers.io

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jrodriguezh.transformers.BuildConfig
import com.jrodriguezh.transformers.data.serializer.TransformerTypeAdapterFactory
import com.jrodriguezh.transformers.modules.scopes.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.reflect.Type
import javax.inject.Named
import javax.inject.Singleton
import kotlin.reflect.typeOf

@Module
class RetrofitModule {

    @Provides
    @Singleton
    @Named("ServiceURL")
    fun serviceUrl(): String = BuildConfig.SERVICE_URL

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient,
                 @Named("ServiceURL") serviceUrl: String,
                 gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(serviceUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun gson(): Gson =
        GsonBuilder().apply {
            //registerTypeAdapter(TransformerTypeAdapter::class.java as Type, TransformerTypeAdapter())
            //registerTypeAdapter(TransformersListTypeAdapter::class.java as Type, TransformersListTypeAdapter())
            registerTypeAdapterFactory(TransformerTypeAdapterFactory())
        }.create()

}