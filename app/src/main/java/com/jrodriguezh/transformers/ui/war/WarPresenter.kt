package com.jrodriguezh.transformers.ui.war

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jrodriguezh.transformers.R
import com.jrodriguezh.transformers.data.Match
import com.jrodriguezh.transformers.data.Transformer
import com.jrodriguezh.transformers.data.War
import com.jrodriguezh.transformers.ui.war.adapters.MatchesAdapter
import com.jrodriguezh.viperarch.Presenter
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_war.*
import okhttp3.internal.filterList
import java.lang.StringBuilder

import kotlin.collections.*

class WarPresenter(router: WarRouter, interactor: WarInteractor) :
    Presenter<WarRouter, WarInteractor>(router, interactor) {

    override fun onViewCreated() {
        val war = prepareWar(view<WarView>()!!.data())
        val adapter = MatchesAdapter(ArrayList(war.matches))
        view<WarView>()!!.list_matches.adapter = adapter
        view<WarView>()!!.button_war.setOnClickListener { startWar(war) }
    }

    fun startWar(war: War) {
        war.battleAll()
        var strBuilder = StringBuilder()
        with(strBuilder) {
            val decepWCount = war.matches.filterList { this.getWinner()?.team?.team == 'D' }.size
            val autobotWCount = war.matches.filterList { this.getWinner()?.team?.team == 'A' }.size

            if (autobotWCount > decepWCount)
                append("Winner: Autobots")
            else if (autobotWCount < decepWCount)
                append("Winner: Decepticons")
            else
                append("Tie")
            append('\n')
            append(war.battlesFinished().let { "Battles: $it" })

            val autSurvivors = war.survivors.filterList { this.team.team == 'A' }
            if (autSurvivors.isNotEmpty())
                append(autSurvivors.joinToString { it.name }.let { "\nSurvivors (Autobots): $it" })
            val decSurvivors = war.survivors.filterList { this.team.team == 'D' }
            if (decSurvivors.isNotEmpty())
                append(decSurvivors.joinToString { it.name }.let { "\nSurvivors (Decepticons): $it" })
        }

            MaterialAlertDialogBuilder(view<WarView>()!!.requireContext())
                    .setMessage(strBuilder.toString()).create().show()



    }

    fun prepareWar(data: List<Transformer?>): War {
        val autobots: MutableList<Transformer?> = data.filterList { this?.team!!.team == 'A' }.toMutableList().apply { sortByDescending { it?.spec?.rank } }
        val decepticons: MutableList<Transformer?> = data.filterList { this?.team!!.team == 'D' }.toMutableList().apply { sortByDescending { it?.spec?.rank } }

        //Fill lists to match sizes
        while (autobots.size < decepticons.size)
            autobots.add(null)

        while (autobots.size > decepticons.size)
            decepticons.add(null)

        val matches = autobots.zip(decepticons) { a: Transformer?, d: Transformer? ->
            Match(a,d)
        }

        return War(matches)
    }

    fun goToDetails(transformer: Transformer = Transformer()) {
        router.openDetailsView(Bundle().apply { putParcelable("transformer", transformer) })
    }
}