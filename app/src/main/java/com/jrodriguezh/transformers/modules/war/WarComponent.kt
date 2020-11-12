package com.jrodriguezh.transformers.modules.war

import android.content.SharedPreferences
import com.jrodriguezh.transformers.io.AllSparkService
import com.jrodriguezh.transformers.io.NetworkComponent
import com.jrodriguezh.transformers.io.TransformersService
import com.jrodriguezh.transformers.modules.main.MainComponent
import com.jrodriguezh.transformers.modules.scopes.FragmentScope
import com.jrodriguezh.transformers.ui.details.TransformerDetailsView
import com.jrodriguezh.transformers.ui.war.WarView
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [ WarModule::class ], dependencies = [ MainComponent::class ])
@FragmentScope
interface WarComponent {
    fun inject(target: WarView)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun fragment(view: WarView): Builder
        fun mainComponent(component: MainComponent): Builder
        fun build(): WarComponent
    }
}