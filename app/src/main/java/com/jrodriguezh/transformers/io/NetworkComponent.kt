package com.jrodriguezh.transformers.io

import android.content.Context
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [HttpLoggerModule::class, RetrofitModule::class])
interface NetworkComponent {

    fun retrofit() : Retrofit

    @Component.Builder
    interface Builder {
        fun build() : NetworkComponent
    }

}
