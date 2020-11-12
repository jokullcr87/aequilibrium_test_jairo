package com.jrodriguezh.viperarch

import com.jrodriguezh.viperarch.contract.InteractorContract
import com.jrodriguezh.viperarch.contract.PresenterContract
import com.jrodriguezh.viperarch.contract.RouterContract
import com.jrodriguezh.viperarch.contract.ViewContract

abstract class Presenter<out R: RouterContract, out I: InteractorContract>
    (protected val router: R, protected val interactor: I)
    : PresenterContract {


    private var boundedView: ViewContract? = null

    protected fun <T: ViewContract> view() : T? = ( boundedView as T?)

    override fun bindView(view: ViewContract) {
        this.boundedView = view
    }

    override fun unbindView() {
        this.boundedView = null
    }

    abstract override fun onViewCreated()
}