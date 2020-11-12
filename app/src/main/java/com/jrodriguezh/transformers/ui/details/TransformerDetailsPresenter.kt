package com.jrodriguezh.transformers.ui.details

import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.TextView
import com.google.android.material.slider.Slider
import com.jrodriguezh.transformers.R
import com.jrodriguezh.transformers.data.Transformer
import com.jrodriguezh.transformers.ui.main.MainView
import com.jrodriguezh.viperarch.Presenter
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*

class TransformerDetailsPresenter(router: TransformerDetailsRouter, interactor: TransformerDetailsInteractor) :
    Presenter<TransformerDetailsRouter, TransformerDetailsInteractor>(router, interactor) {

    override fun onViewCreated() {
        val myView: TransformerDetailsView = view()!!
        val theView = myView.requireView()
        myView.data.let {
            theView.findViewById<View>(R.id.frm_team)
                    .findViewById<AutoCompleteTextView>(R.id.txt_autoCompleteTeam)
                    .setText(teamFromChar(it.team.team), false)
            theView.findViewById<View>(R.id.frm_name)
                    .findViewById<TextView>(R.id.txt_name)
                    .setText(it.name)
            theView.findViewById<View>(R.id.frm_skill)
                    .findViewById<Slider>(R.id.slider_skill).value = it.spec.skill.toFloat()
            theView.findViewById<View>(R.id.frm_rank)
                    .findViewById<Slider>(R.id.slider_rank).value = it.spec.rank.toFloat()
            theView.findViewById<View>(R.id.frm_strength)
                    .findViewById<Slider>(R.id.slider_strength).value = it.spec.strength.toFloat()
            theView.findViewById<View>(R.id.frm_intelligence)
                    .findViewById<Slider>(R.id.slider_intelligence).value = it.spec.intelligence.toFloat()
            theView.findViewById<View>(R.id.frm_speed)
                    .findViewById<Slider>(R.id.slider_speed).value = it.spec.speed.toFloat()
            theView.findViewById<View>(R.id.frm_endurance)
                    .findViewById<Slider>(R.id.slider_endurance).value = it.spec.endurance.toFloat()
            theView.findViewById<View>(R.id.frm_courage)
                    .findViewById<Slider>(R.id.slider_courage).value = it.spec.courage.toFloat()
            theView.findViewById<View>(R.id.frm_firepower)
                    .findViewById<Slider>(R.id.slider_firepower).value = it.spec.firepower.toFloat()
        }
    }

    fun saveInformation() {
        val myView: TransformerDetailsView = view()!!
        val theView = myView.requireView()
        myView.data.apply {
            name = theView.findViewById<View>(R.id.frm_name)
                    .findViewById<TextView>(R.id.txt_name)
                    .text.toString()

            team.team = theView.findViewById<View>(R.id.frm_team)
                    .findViewById<AutoCompleteTextView>(R.id.txt_autoCompleteTeam)
                    .text[0]

            spec.skill = theView.findViewById<View>(R.id.frm_skill)
                    .findViewById<Slider>(R.id.slider_skill)
                    .value.toInt().toByte()

            spec.rank = theView.findViewById<View>(R.id.frm_rank)
                    .findViewById<Slider>(R.id.slider_rank)
                    .value.toInt().toByte()

            spec.strength = theView.findViewById<View>(R.id.frm_strength)
                    .findViewById<Slider>(R.id.slider_strength)
                    .value.toInt().toByte()

            spec.intelligence = theView.findViewById<View>(R.id.frm_intelligence)
                    .findViewById<Slider>(R.id.slider_intelligence)
                    .value.toInt().toByte()

            spec.speed = theView.findViewById<View>(R.id.frm_speed)
                    .findViewById<Slider>(R.id.slider_speed)
                    .value.toInt().toByte()

            spec.endurance = theView.findViewById<View>(R.id.frm_endurance)
                    .findViewById<Slider>(R.id.slider_endurance)
                    .value.toInt().toByte()

            spec.courage = theView.findViewById<View>(R.id.frm_courage)
                    .findViewById<Slider>(R.id.slider_courage)
                    .value.toInt().toByte()

            spec.firepower = theView.findViewById<View>(R.id.frm_firepower)
                    .findViewById<Slider>(R.id.slider_firepower)
                    .value.toInt().toByte()
        }.also {
            interactor.saveDetails(it, {
                var list: ArrayList<Transformer> = (myView.requireActivity() as MainView).data!!
                
                val index = list.indexOf(it)
                if (index > -1) {
                    list.removeAt(index)
                    list.add(index, it)
                } else {
                    list.add(it)
                }

                (myView.requireActivity() as MainView).data = list

                router.back()
            }, {

            })
        }
    }

    fun delete(id: String) {
        interactor.delete(id, {
            val myView: TransformerDetailsView = view()!!
            var list: ArrayList<Transformer> = (myView.requireActivity() as MainView).data!!

            val index = list.indexOf(Transformer(id))
            list.removeAt(index)

            (myView.requireActivity() as MainView).data = list

            router.back()
        }, {

        })
    }

    private fun teamFromChar(char: Char): String =
        when (char) {
            'A' -> "Autobots"
            'D' -> "Decepticons"
            else -> ""
        }

}