package com.jrodriguezh.transformers

import android.app.Application
import com.jrodriguezh.transformers.io.DaggerNetworkComponent
import com.jrodriguezh.transformers.io.NetworkComponent
import com.jrodriguezh.transformers.modules.main.DaggerMainComponent
import com.jrodriguezh.transformers.modules.main.MainComponent
import com.jrodriguezh.transformers.ui.main.MainPresenter
import javax.inject.Inject

class TransformersApplication : Application() {

    val networkComponent: NetworkComponent by lazy {
        DaggerNetworkComponent.builder()
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }

}