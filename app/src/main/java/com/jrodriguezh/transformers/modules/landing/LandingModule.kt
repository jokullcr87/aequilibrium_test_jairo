package com.jrodriguezh.transformers.modules.landing

import android.content.Context
import android.content.SharedPreferences
import com.jrodriguezh.transformers.io.AllSparkService
import com.jrodriguezh.transformers.io.TransformersService
import com.jrodriguezh.transformers.modules.scopes.ActivityScope
import com.jrodriguezh.transformers.ui.landing.LandingInteractor
import com.jrodriguezh.transformers.ui.landing.LandingPresenter
import com.jrodriguezh.transformers.ui.landing.LandingRouter
import com.jrodriguezh.transformers.ui.landing.LandingView
import com.jrodriguezh.viperarch.ActivityView
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module//(includes = [MainSetupModule::class])
class LandingModule {
    @Provides
    @ActivityScope
    fun providesPresenter(router: LandingRouter, interactor: LandingInteractor): LandingPresenter =
        LandingPresenter(router, interactor)

    @Provides
    @ActivityScope
    fun providesRouter(view: LandingView): LandingRouter = LandingRouter(view)

    @Provides
    @ActivityScope
    fun providesInteractor(allSparkService: AllSparkService,
                           transformersService: TransformersService,
                           preferences: SharedPreferences): LandingInteractor =
        LandingInteractor(allSparkService, transformersService, preferences)

    @Provides
    @ActivityScope
    fun providesActivity(view: LandingView): ActivityView = view

    @Provides
    @ActivityScope
    fun providesPreferences(view: LandingView): SharedPreferences =
        view.getSharedPreferences("transformers_prefs", Context.MODE_PRIVATE)

    @Provides
    @ActivityScope
    fun provideAllSparkService(retrofit: Retrofit): AllSparkService =
        retrofit.create(AllSparkService::class.java)

    @Provides
    @ActivityScope
    fun provideTransformersService(retrofit: Retrofit): TransformersService =
        retrofit.create(TransformersService::class.java)
}