package com.jrodriguezh.transformers.ui.main

import com.jrodriguezh.viperarch.Presenter

class MainPresenter(router: MainRouter, interactor: MainInteractor) :
    Presenter<MainRouter, MainInteractor>(router, interactor) {

    override fun onViewCreated() {

    }


}