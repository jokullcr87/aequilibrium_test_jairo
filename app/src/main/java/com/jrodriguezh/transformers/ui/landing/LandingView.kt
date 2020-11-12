package com.jrodriguezh.transformers.ui.landing

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jrodriguezh.transformers.R
import com.jrodriguezh.transformers.TransformersApplication
import com.jrodriguezh.transformers.modules.landing.DaggerLandingComponent
import com.jrodriguezh.transformers.modules.landing.LandingComponent
import com.jrodriguezh.transformers.modules.main.DaggerMainComponent
import com.jrodriguezh.transformers.modules.main.MainComponent
import com.jrodriguezh.transformers.ui.main.MainPresenter
import com.jrodriguezh.viperarch.ActivityView
import com.jrodriguezh.viperarch.contract.PresenterContract
import javax.inject.Inject

class LandingView : ActivityView() {

    @Inject
    lateinit var presenter : LandingPresenter

    val component: LandingComponent by lazy {
        DaggerLandingComponent.builder()
            .activity(this)
            .networkComponent((application as TransformersApplication).networkComponent)
            .build()
    }

    override fun presenter() : PresenterContract = presenter

    override fun injectData(view: Activity) {
        component.inject(this)
    }

    override fun initView() {
        setContentView(R.layout.activity_landing_view)

        val btnStart: Button = findViewById(R.id.button_start)
        btnStart.setOnClickListener { presenter.startClicked() }
    }

}