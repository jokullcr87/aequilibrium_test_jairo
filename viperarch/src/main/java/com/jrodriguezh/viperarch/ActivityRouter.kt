package com.jrodriguezh.viperarch

import com.jrodriguezh.viperarch.contract.RouterContract

abstract class ActivityRouter(private val activity: ActivityView) : RouterContract {

    override fun finish() {
        activity.finish()
    }

}