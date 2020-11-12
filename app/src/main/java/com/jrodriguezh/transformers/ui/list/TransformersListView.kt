package com.jrodriguezh.transformers.ui.list

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
import com.jrodriguezh.transformers.ui.list.adapters.TransformersAdapter
import com.jrodriguezh.viperarch.FragmentView
import com.jrodriguezh.viperarch.contract.PresenterContract
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class TransformersListView : FragmentView() {

    @Inject
    lateinit var presenter : TransformersListPresenter

    val component: ListComponent by lazy {
        DaggerListComponent.builder()
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
        list_transformers.layoutManager = LinearLayoutManager(this.context)
        val adapter = TransformersAdapter(data())
        adapter.itemClick = {
            presenter.goToDetails(it)
        }
        list_transformers.adapter = adapter
        fab_add.setOnClickListener { presenter.goToDetails() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, null)
    }
/*
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }*/
}