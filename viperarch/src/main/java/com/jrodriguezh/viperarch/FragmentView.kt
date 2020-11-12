package com.jrodriguezh.viperarch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.jrodriguezh.viperarch.contract.PresenterContract
import com.jrodriguezh.viperarch.contract.ViewContract

abstract class FragmentView : Fragment(), ViewContract {

    companion object {
        inline fun <reified T: FragmentView> launch(activityView: ActivityView,
                                                    params: Bundle? = null,
                                                    @IdRes container: Int) {
            val fragmentClass : Class<out Fragment> = T::class.java
            val fragment = fragmentClass.newInstance()
            fragment.arguments = params
            activityView.supportFragmentManager.beginTransaction()
                .replace(container, fragment)
                .commit()
        }
    }

    protected abstract fun presenter() : PresenterContract

    abstract fun injectData(view: FragmentView)
    abstract fun initView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        injectData(this)
        presenter().bindView(this)
        presenter().onViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter().unbindView()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {

    }

}