package com.jrodriguezh.viperarch.contract

import androidx.core.app.BundleCompat
import androidx.core.content.ContextCompat

interface ViewContract {

    fun showLoading()
    fun hideLoading()
    fun showMessage(message: String)

}