package com.jrodriguezh.transformers.ui.details

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jrodriguezh.transformers.R
import com.jrodriguezh.transformers.data.Transformer
import com.jrodriguezh.transformers.modules.details.DaggerDetailsComponent
import com.jrodriguezh.transformers.modules.details.DetailsComponent
import com.jrodriguezh.transformers.ui.main.MainView
import com.jrodriguezh.viperarch.ActivityView
import com.jrodriguezh.viperarch.FragmentView
import com.jrodriguezh.viperarch.contract.PresenterContract
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import javax.inject.Inject

class TransformerDetailsView : FragmentView() {

    @Inject
    lateinit var presenter : TransformerDetailsPresenter

    val component: DetailsComponent by lazy {
        DaggerDetailsComponent.builder()
            .mainComponent((this.activity as MainView).component)
            .fragment(this)
            .build()
    }

    val data: Transformer by lazy {
        arguments?.getParcelable("transformer")!!
    }

    override fun presenter() : PresenterContract = presenter

    override fun injectData(view: FragmentView) {
        component.inject(this)
    }

    override fun initView() {
        setHasOptionsMenu(true)
        val items = listOf("Autobots", "Decepticons")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        (frm_team.txt_autoCompleteTeam as? AutoCompleteTextView)?.setAdapter(adapter)
        button_save?.setOnClickListener { presenter.saveInformation() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, null)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        data.id?.let { inflater.inflate(R.menu.details_menu, menu) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            presenter.delete(data.id!!)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}