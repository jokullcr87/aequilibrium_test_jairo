package com.jrodriguezh.transformers.modules.list

import com.jrodriguezh.transformers.io.AllSparkService
import com.jrodriguezh.transformers.modules.scopes.FragmentScope
import com.jrodriguezh.transformers.ui.list.TransformersListInteractor
import com.jrodriguezh.transformers.ui.list.TransformersListPresenter
import com.jrodriguezh.transformers.ui.list.TransformersListRouter
import com.jrodriguezh.transformers.ui.list.TransformersListView
import com.jrodriguezh.viperarch.ActivityView
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ListModule {
    @Provides
    @FragmentScope
    fun providesPresenter(router: TransformersListRouter,
                          interactor: TransformersListInteractor): TransformersListPresenter =
        TransformersListPresenter(router, interactor)

    @Provides
    @FragmentScope
    fun providesRouter(activity: ActivityView,
                       view: TransformersListView): TransformersListRouter =
        TransformersListRouter(activity, view)

    @Provides
    @FragmentScope
    fun providesInteractor(retrofit: Retrofit): TransformersListInteractor =
        TransformersListInteractor(retrofit.create(AllSparkService::class.java))
}