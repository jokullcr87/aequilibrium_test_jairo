package com.jrodriguezh.transformers.ui.landing

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.jrodriguezh.transformers.R
import com.jrodriguezh.transformers.ui.details.TransformerDetailsView
import com.jrodriguezh.transformers.ui.list.TransformersListView
import com.jrodriguezh.transformers.ui.main.MainView
import com.jrodriguezh.viperarch.ActivityRouter
import com.jrodriguezh.viperarch.ActivityView
import com.jrodriguezh.viperarch.FragmentRouter

class LandingRouter(private val activity: LandingView) :
    ActivityRouter(activity) {

    fun openMainView(args: Bundle?) {
        ActivityView.launch<MainView>(activity, args)
        finish()
    }

}