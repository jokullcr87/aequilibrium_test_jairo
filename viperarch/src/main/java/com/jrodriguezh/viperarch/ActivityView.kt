package com.jrodriguezh.viperarch

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.jrodriguezh.viperarch.contract.PresenterContract
import com.jrodriguezh.viperarch.contract.ViewContract

abstract class ActivityView : AppCompatActivity(), ViewContract {

    companion object {
        inline fun <reified T: ActivityView> launch(context: Context, params: Bundle? = null) {
            val intent = Intent(context, T::class.java)
            params?.let(intent::putExtras)
            ContextCompat.startActivity(context, intent, null)
        }
    }

    protected abstract fun presenter() : PresenterContract

    abstract fun injectData(view: Activity)
    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        injectData(this)
        presenter().bindView(this)
        presenter().onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter().unbindView()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {

    }

}