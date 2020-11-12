package com.jrodriguezh.transformers.io

import com.jrodriguezh.transformers.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class HttpLoggerModule {
    @Provides
    @Singleton
    fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor?): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(150, TimeUnit.SECONDS)
            .connectTimeout(150, TimeUnit.SECONDS)
            .apply {
                httpLoggingInterceptor?.let ( ::addInterceptor )
            }
            .build()

    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor? =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        } else null
}
