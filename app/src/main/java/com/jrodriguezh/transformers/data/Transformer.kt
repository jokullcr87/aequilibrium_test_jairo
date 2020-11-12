package com.jrodriguezh.transformers.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlin.math.abs

@Parcelize
data class Transformer(val id: String? = null, var name: String = "", val team: Team = Team('A'), val spec: TechSpec = TechSpec()) : Parcelable, Comparable<Transformer>  {

    override fun compareTo(other: Transformer): Int {
        return if (id != null && other.id != null)
                    id.compareTo(other.id)
                else if (id == null && other.id != null)
                    Int.MIN_VALUE
                else
                    Int.MAX_VALUE
    }

    override fun equals(other: Any?): Boolean {
        if (other is Transformer) {
            if (this.id != null && other.id != null)
                return this.id == other.id
        }
        return false
    }

    // Returns positive if object would win on a battle,
    // negative if object would lose on a battle,
    // and 0 if battle result would be a tie
    fun measureAgainst(other: Transformer): Int {
        val isBoss = isBoss()
        val againstBoss = other.isBoss()
        if (isBoss && againstBoss)
            return 0
        else if (isBoss)
            return Int.MAX_VALUE
        else if (againstBoss)
            return Int.MIN_VALUE

        val courageTest = spec.courage - other.spec.courage
        val strengthTest = spec.strength - other.spec.strength
        if (abs(courageTest+strengthTest) >= 7 && abs(courageTest) >= 4 && abs(strengthTest) >=3) {
            return if (spec.courage > other.spec.courage) 10000 else -10000
        }

        val skillTest = spec.skill - other.spec.skill
        if (abs(skillTest) >= 3) {
            return if (spec.skill > other.spec.skill) 100000 else -100000
        }

        return spec.overall() - other.spec.overall()
    }

    fun isBoss(): Boolean = this.name.toLowerCase().let { it == "optimus prime" || it == "predaking" }
}