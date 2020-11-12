package com.jrodriguezh.transformers.ui.details

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.jrodriguezh.transformers.R
import com.jrodriguezh.viperarch.ActivityView
import com.jrodriguezh.viperarch.FragmentRouter

class TransformerDetailsRouter(activity: ActivityView, fragment: TransformerDetailsView) :
    FragmentRouter<TransformerDetailsView>(activity, fragment) {

    fun back() {
        val navHostFragment = activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.navigateUp()
    }

}