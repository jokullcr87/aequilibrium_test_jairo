package com.jrodriguezh.transformers.modules.landing

import com.jrodriguezh.transformers.io.NetworkComponent
import com.jrodriguezh.transformers.modules.scopes.ActivityScope
import com.jrodriguezh.transformers.ui.landing.LandingView
import com.jrodriguezh.viperarch.ActivityView
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [ LandingModule::class ], dependencies = [ NetworkComponent::class ])
@ActivityScope
interface LandingComponent {
    fun inject(target: LandingView)
    fun activity() : ActivityView
    fun retrofit(): Retrofit

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(view: LandingView): Builder
        fun networkComponent(component: NetworkComponent): Builder
        fun build(): LandingComponent
    }
}