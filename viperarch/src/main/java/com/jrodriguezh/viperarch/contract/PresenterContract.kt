package com.jrodriguezh.viperarch.contract

interface PresenterContract : ViewBindable<ViewContract> {

    fun onViewCreated()
}