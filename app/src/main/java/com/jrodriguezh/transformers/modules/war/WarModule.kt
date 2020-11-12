package com.jrodriguezh.transformers.modules.war

import android.content.Context
import android.content.SharedPreferences
import com.jrodriguezh.transformers.io.AllSparkService
import com.jrodriguezh.transformers.io.TransformersService
import com.jrodriguezh.transformers.modules.scopes.ActivityScope
import com.jrodriguezh.transformers.modules.scopes.FragmentScope
import com.jrodriguezh.transformers.ui.details.TransformerDetailsInteractor
import com.jrodriguezh.transformers.ui.details.TransformerDetailsPresenter
import com.jrodriguezh.transformers.ui.details.TransformerDetailsRouter
import com.jrodriguezh.transformers.ui.details.TransformerDetailsView
import com.jrodriguezh.transformers.ui.main.MainView
import com.jrodriguezh.transformers.ui.war.WarInteractor
import com.jrodriguezh.transformers.ui.war.WarPresenter
import com.jrodriguezh.transformers.ui.war.WarRouter
import com.jrodriguezh.transformers.ui.war.WarView
import com.jrodriguezh.viperarch.ActivityView
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class WarModule {

    @Provides
    @FragmentScope
    fun providesPresenter(router: WarRouter,
                          interactor: WarInteractor
    ): WarPresenter =
        WarPresenter(router, interactor)

    @Provides
    @FragmentScope
    fun providesRouter(activity: ActivityView,
                       view: WarView
    ): WarRouter =
        WarRouter(activity, view)

    @Provides
    @FragmentScope
    fun providesInteractor(transformersService: TransformersService, preferences: SharedPreferences):
            WarInteractor = WarInteractor(transformersService, preferences)
}