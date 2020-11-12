package com.jrodriguezh.transformers.ui.main

import android.os.Bundle
import com.jrodriguezh.transformers.R
import com.jrodriguezh.transformers.ui.list.TransformersListView
import com.jrodriguezh.viperarch.FragmentView
import com.jrodriguezh.viperarch.contract.RouterContract

class MainRouter(private val view: MainView) : RouterContract {

    override fun finish() {
        view.finish()
    }

    fun openListView(params: Bundle?) {
        FragmentView.launch<TransformersListView>(view, params, R.id.container)
    }
}