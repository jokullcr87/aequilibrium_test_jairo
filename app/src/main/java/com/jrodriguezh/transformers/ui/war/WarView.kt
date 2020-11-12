package com.jrodriguezh.transformers.ui.war

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.jrodriguezh.transformers.R
import com.jrodriguezh.transformers.data.Transformer
import com.jrodriguezh.transformers.modules.list.DaggerListComponent
import com.jrodriguezh.transformers.ui.main.MainView
import com.jrodriguezh.transformers.modules.list.ListComponent
import com.jrodriguezh.transformers.modules.war.DaggerWarComponent
import com.jrodriguezh.transformers.modules.war.WarComponent
import com.jrodriguezh.transformers.ui.list.adapters.TransformersAdapter
import com.jrodriguezh.transformers.ui.war.adapters.MatchesAdapter
import com.jrodriguezh.viperarch.FragmentView
import com.jrodriguezh.viperarch.contract.PresenterContract
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_war.*
import javax.inject.Inject

class WarView : FragmentView() {

    @Inject
    lateinit var presenter : WarPresenter

    val component: WarComponent by lazy {
        DaggerWarComponent.builder()
            .mainComponent((this.activity as MainView).component)
            .fragment(this)
            .build()
    }

    fun data(): ArrayList<Transformer> =
            (requireActivity() as MainView).data!!

    override fun presenter() : PresenterContract = presenter

    override fun injectData(view: FragmentView) {
        component.inject(this)
    }

    override fun initView() {
        list_matches.layoutManager = LinearLayoutManager(this.context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_war, null)
    }
}