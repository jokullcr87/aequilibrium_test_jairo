package com.jrodriguezh.transformers.modules.list

import com.jrodriguezh.transformers.io.NetworkComponent
import com.jrodriguezh.transformers.modules.main.MainComponent
import com.jrodriguezh.transformers.modules.scopes.FragmentScope
import com.jrodriguezh.transformers.ui.list.TransformersListView
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ ListModule::class ], dependencies = [ MainComponent::class ])
@FragmentScope
interface ListComponent {
    fun inject(target: TransformersListView)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun fragment(view: TransformersListView): Builder
        fun mainComponent(component: MainComponent): Builder
        fun build(): ListComponent
    }
}