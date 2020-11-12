package com.jrodriguezh.transformers.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(var team: Char, var icon: String? = null): Parcelable {
}