package com.jrodriguezh.transformers.data

class War(val matches: List<Match> = mutableListOf()) {

    val survivors = mutableListOf<Transformer>()
    private var battleCount: Int = 0

    fun battlesFinished(): Int = battleCount

    fun battleAll() {
        var isAnnihilation: Boolean = false
        for (match in matches) {
            match.engage().also {
                isAnnihilation = isAnnihilation ||
                        it.getResult()?.let { result ->
                    result == Match.BattleResult.Annihilation
                } ?: false
                if (it.getResult() != null) {
                    when (it.getResult()) {
                        Match.BattleResult.Ended, Match.BattleResult.DidNotBattle -> survivors.add(it.getWinner()!!)
                        Match.BattleResult.RanAway -> survivors.addAll(listOf(it.t1!!, it.t2!!))
                    }
                    if (it.getResult() == Match.BattleResult.Ended || it.getResult() == Match.BattleResult.Tie)
                        battleCount+=1
                }
            }

            if (isAnnihilation) break
        }

        if (isAnnihilation) {
            matches.forEach { match ->
                val result = match.getResult()
                if (result == null || result != Match.BattleResult.Annihilation)
                    match.annihilate()
            }
            survivors.clear()
            battleCount = 1
        }
    }
}