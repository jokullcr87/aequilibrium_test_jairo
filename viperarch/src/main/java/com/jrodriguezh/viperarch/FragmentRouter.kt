package com.jrodriguezh.viperarch

import com.jrodriguezh.viperarch.contract.RouterContract

abstract class FragmentRouter<out T: FragmentView>(protected val activity: ActivityView,
                                                   protected val fragment: T) : RouterContract {

    override fun finish() {
        activity.supportFragmentManager.beginTransaction().remove(fragment).commit()
    }

}