package com.jrodriguezh.transformers.modules.details

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
import com.jrodriguezh.viperarch.ActivityView
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DetailsModule {

    @Provides
    @FragmentScope
    fun providesPresenter(router: TransformerDetailsRouter,
                          interactor: TransformerDetailsInteractor
    ): TransformerDetailsPresenter =
        TransformerDetailsPresenter(router, interactor)

    @Provides
    @FragmentScope
    fun providesRouter(activity: ActivityView,
                       view: TransformerDetailsView
    ): TransformerDetailsRouter =
        TransformerDetailsRouter(activity, view)

    @Provides
    @FragmentScope
    fun providesInteractor(transformersService: TransformersService, preferences: SharedPreferences):
            TransformerDetailsInteractor = TransformerDetailsInteractor(transformersService, preferences)
}