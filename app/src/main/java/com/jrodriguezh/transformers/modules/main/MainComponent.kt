package com.jrodriguezh.transformers.modules.main

import android.content.SharedPreferences
import com.jrodriguezh.transformers.io.AllSparkService
import com.jrodriguezh.transformers.io.NetworkComponent
import com.jrodriguezh.transformers.io.TransformersService
import com.jrodriguezh.transformers.ui.main.MainView
import com.jrodriguezh.transformers.modules.scopes.ActivityScope
import com.jrodriguezh.viperarch.ActivityView
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [ MainModule::class ], dependencies = [ NetworkComponent::class ])
@ActivityScope
interface MainComponent {
    fun inject(target: MainView)
    fun activity() : ActivityView

    fun retrofit(): Retrofit
    fun transformersService(): TransformersService
    fun preferences(): SharedPreferences

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(view: MainView): Builder
        fun networkComponent(component: NetworkComponent): Builder
        fun build(): MainComponent
    }
}