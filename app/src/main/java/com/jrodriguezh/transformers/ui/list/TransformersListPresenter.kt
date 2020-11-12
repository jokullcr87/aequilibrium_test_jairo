package com.jrodriguezh.transformers.ui.list

import android.os.Bundle
import android.widget.TextView
import com.jrodriguezh.transformers.R
import com.jrodriguezh.transformers.data.Transformer
import com.jrodriguezh.viperarch.Presenter
import kotlinx.android.synthetic.main.fragment_list.view.*

class TransformersListPresenter(router: TransformersListRouter, interactor: TransformersListInteractor) :
    Presenter<TransformersListRouter, TransformersListInteractor>(router, interactor) {

    override fun onViewCreated() {

    }

    fun goToDetails(transformer: Transformer = Transformer()) {
        router.openDetailsView(Bundle().apply { putParcelable("transformer", transformer) })
    }

}