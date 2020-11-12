package com.jrodriguezh.transformers.ui.main

import android.app.Activity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.jrodriguezh.transformers.R
import com.jrodriguezh.transformers.TransformersApplication
import com.jrodriguezh.transformers.data.Transformer
import com.jrodriguezh.transformers.modules.main.DaggerMainComponent
import com.jrodriguezh.transformers.modules.main.MainComponent
import com.jrodriguezh.viperarch.ActivityView
import com.jrodriguezh.viperarch.contract.PresenterContract
import javax.inject.Inject

class MainView : ActivityView() {

    @Inject
    lateinit var presenter : MainPresenter

    var data : ArrayList<Transformer> = ArrayList(emptyList())

    val component: MainComponent by lazy {
        DaggerMainComponent.builder()
            .activity(this)
            .networkComponent((application as TransformersApplication).networkComponent)
            .build()
    }

    override fun presenter() : PresenterContract = presenter

    override fun injectData(view: Activity) {
        component.inject(this)
    }

    override fun initView() {
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_list, R.id.navigation_war
        ))

        setupActionBarWithNavController(this, navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        data = intent.extras?.getParcelableArrayList("data") ?: ArrayList(emptyList())
    }
}