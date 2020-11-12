package com.jrodriguezh.transformers.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TechSpec(
    var strength: Byte = 1,
    var intelligence: Byte = 1,
    var speed: Byte = 1,
    var endurance: Byte = 1,
    var rank: Byte = 1,
    var courage: Byte = 1,
    var firepower: Byte = 1,
    var skill: Byte = 1) : Parcelable {

    fun overall(): Int = strength + intelligence + speed + endurance + firepower
}