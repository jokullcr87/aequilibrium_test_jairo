package com.jrodriguezh.transformers.data

import kotlin.math.abs

class Match(val t1: Transformer?, val t2: Transformer?) {

    enum class State(var value: Boolean) {
        NotStarted(false),
        Finished(true)
    }

    enum class BattleResult(var value: Int?) {
        Annihilation(Int.MAX_VALUE),
        DidNotBattle(-0xFF),
        Tie(0),
        RanAway(-0xF),
        Ended(1)
    }

    private var state: State = State.NotStarted
    private var result: BattleResult? = null
    private var winner: Transformer? = null

    fun getResult(): BattleResult? = result

    fun getState(): State = state

    fun getWinner(): Transformer? = winner

    fun annihilate() {
        result = BattleResult.Annihilation
        state = State.Finished
        winner = null
    }

    fun engage(): Match {
        if (t1 == null || t2 == null){
            result = BattleResult.DidNotBattle
            winner = if (t1 == null) t2 else t1
            state = State.Finished
            return this
        }

        val comparison = t1.measureAgainst(t2)
        result = when (comparison) {
            0 -> if (t1.isBoss() && t2.isBoss()) BattleResult.Annihilation else BattleResult.Tie
            10000, -10000 -> BattleResult.RanAway
            else -> BattleResult.Ended
        }

        state = State.Finished

        if (result == BattleResult.Annihilation) return this

        winner = if (comparison > 0) t1 else t2

        return this
    }
}