package com.jrodriguezh.transformers.modules.scopes

import kotlin.annotation.Retention
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope() {

}