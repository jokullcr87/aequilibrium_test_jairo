package com.jrodriguezh.transformers.ui.landing

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.jrodriguezh.transformers.R
import com.jrodriguezh.transformers.TransformersApplication
import com.jrodriguezh.viperarch.Presenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async

class LandingPresenter(router: LandingRouter, interactor: LandingInteractor) :
    Presenter<LandingRouter, LandingInteractor>(router, interactor) {

    override fun onViewCreated() {
        storedToken()?.let {
            val landing: LandingView = view()!!
            val txtLoading: TextView = landing.findViewById(R.id.text_loading)
            txtLoading.visibility = View.VISIBLE
            interactor.getTransformers({ transformers ->
                Bundle().apply {
                    putParcelableArrayList("data", ArrayList(transformers))
                }.run {
                    router.openMainView(this)
                }
            }, {
                setupLanding()
            })
        }
        setupLanding()
    }

    fun startClicked() {
        interactor.token (
            {
                saveToken(it).let {
                    interactor.storedToken()
                    router.openMainView(null)
                }
            }, {
                it?.message?.let { msg -> view<LandingView>()!!.showMessage(msg) }
            })
    }

    private fun setupLanding() {
        val landing: LandingView = view()!!
        val btnStart: Button = landing.findViewById(R.id.button_start)
        val txtLoading: TextView = landing.findViewById(R.id.text_loading)

        if (hasToken()) {
            txtLoading.visibility = View.VISIBLE
        } else {
            btnStart.visibility = View.VISIBLE
        }
    }

    private fun hasToken(): Boolean = interactor.checkToken()

    private fun storedToken(): String? = interactor.storedToken()

    private fun saveToken(token: String): String {
        interactor.saveToken(token)
        return token
    }

    fun getToken() {
        interactor.token(::saveToken) {
            it?.message?.let { msg -> view<LandingView>()!!.showMessage(msg) }
        }
    }
}