package com.jrodriguezh.transformers.ui.list

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.jrodriguezh.transformers.R
import com.jrodriguezh.transformers.ui.details.TransformerDetailsView
import com.jrodriguezh.viperarch.ActivityView
import com.jrodriguezh.viperarch.FragmentRouter

class TransformersListRouter(activity: ActivityView, fragment: TransformersListView) :
    FragmentRouter<TransformersListView>(activity, fragment) {

    fun openDetailsView(args: Bundle?) {
        val navHostFragment = activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.navigate(R.id.navigation_transformer_details, args)
    }

}