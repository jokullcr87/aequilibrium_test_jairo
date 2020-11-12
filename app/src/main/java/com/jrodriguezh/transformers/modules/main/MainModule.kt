package com.jrodriguezh.transformers.modules.main

import android.content.Context
import android.content.SharedPreferences
import com.jrodriguezh.transformers.io.AllSparkService
import com.jrodriguezh.transformers.io.TransformersService
import com.jrodriguezh.transformers.ui.main.MainView
import com.jrodriguezh.transformers.ui.main.MainInteractor
import com.jrodriguezh.transformers.ui.main.MainPresenter
import com.jrodriguezh.transformers.ui.main.MainRouter
import com.jrodriguezh.transformers.modules.scopes.ActivityScope
import com.jrodriguezh.transformers.ui.landing.LandingView
import com.jrodriguezh.viperarch.ActivityView
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module//(includes = [MainSetupModule::class])
class MainModule {
    @Provides
    @ActivityScope
    fun providesPresenter(router: MainRouter, interactor: MainInteractor): MainPresenter =
        MainPresenter(router, interactor)

    @Provides
    @ActivityScope
    fun providesRouter(view: MainView): MainRouter = MainRouter(view)

    @Provides
    @ActivityScope
    fun providesInteractor(): MainInteractor = MainInteractor()

    @Provides
    @ActivityScope
    fun providesActivity(view: MainView): ActivityView = view

    @Provides
    @ActivityScope
    fun providesPreferences(view: MainView): SharedPreferences =
        view.getSharedPreferences("transformers_prefs", Context.MODE_PRIVATE)

    @Provides
    @ActivityScope
    fun provideTransformersService(retrofit: Retrofit): TransformersService =
        retrofit.create(TransformersService::class.java)

}