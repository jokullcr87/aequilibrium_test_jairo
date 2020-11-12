package com.jrodriguezh.transformers.modules.details

import android.content.SharedPreferences
import com.jrodriguezh.transformers.io.AllSparkService
import com.jrodriguezh.transformers.io.NetworkComponent
import com.jrodriguezh.transformers.io.TransformersService
import com.jrodriguezh.transformers.modules.main.MainComponent
import com.jrodriguezh.transformers.modules.scopes.FragmentScope
import com.jrodriguezh.transformers.ui.details.TransformerDetailsView
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [ DetailsModule::class ], dependencies = [ MainComponent::class ])
@FragmentScope
interface DetailsComponent {
    fun inject(target: TransformerDetailsView)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun fragment(view: TransformerDetailsView): Builder
        fun mainComponent(component: MainComponent): Builder
        fun build(): DetailsComponent
    }
}