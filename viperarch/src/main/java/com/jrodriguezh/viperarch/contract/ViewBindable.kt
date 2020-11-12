package com.jrodriguezh.viperarch.contract

interface ViewBindable<V: ViewContract> {

    fun bindView(view: V)
    fun unbindView()

}