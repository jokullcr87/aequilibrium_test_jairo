package com.jrodriguezh.transformers

import com.jrodriguezh.transformers.data.Match
import com.jrodriguezh.transformers.data.TechSpec
import com.jrodriguezh.transformers.data.Transformer
import org.junit.Test

class BattleUnitTest {

    @Test
    fun bossAgainstOtherTest() {
        val boss = Transformer(name="Optimus Prime")
        val other = Transformer(name="Test Transformer",
                            spec = TechSpec(4,6,6,
                                    5,8,5,7,3))

        val match = Match(boss, other)
        match.engage().let {
            val result = it.getResult()!!
            assert(result == Match.BattleResult.Ended) { "Battle must've ocurred" }
            assert(it.getWinner()!!.name == boss.name) { "The boss transformer must won" }
            assert(it.getState() == Match.State.Finished) { "Battle must been finished" }
        }
    }

    @Test
    fun bossAgainstBossTest() {
        val boss = Transformer(name="Optimus Prime")
        val other = Transformer(name="Predaking",
                spec = TechSpec(4,6,6,
                        5,8,5,7,3))

        val match = Match(boss, other)
        match.engage().let {
            val result = it.getResult()!!
            assert(result == Match.BattleResult.Annihilation) { "Battle must've end with total annihilation" }
            assert(it.getWinner() == null) { "The boss transformer must won" }
            assert(it.getState() == Match.State.Finished) { "Battle must been finished" }
        }
    }

    @Test
    fun strengthAndCourageTest() {
        val bot = Transformer(name="Test Bot",
                spec = TechSpec(1,6,6,
                        5,8,1,7,3))
        val other = Transformer(name="Test Transformer",
                spec = TechSpec(4,6,6,
                        5,8,5,7,3))

        val match = Match(bot, other)
        match.engage().let {
            val result = it.getResult()!!
            assert(result == Match.BattleResult.RanAway) { "Battle must've not ocurred" }
            assert(it.getWinner()!!.name == other.name) { "The other transformer must won" }
            assert(it.getState() == Match.State.Finished) { "Battle must been finished" }
        }
    }

    @Test
    fun skillTest() {
        val bot = Transformer(name="Test Bot",
                spec = TechSpec(4,6,6,
                        5,8,5,7,9))
        val other = Transformer(name="Test Transformer",
                spec = TechSpec(4,6,6,
                        5,8,5,7,3))

        val match = Match(bot, other)
        match.engage().let {
            val result = it.getResult()!!
            assert(result == Match.BattleResult.Ended) { "Battle must've not ocurred" }
            assert(it.getWinner()!!.name == bot.name) { "The bot transformer must won" }
            assert(it.getState() == Match.State.Finished) { "Battle must been finished" }
        }
    }

}