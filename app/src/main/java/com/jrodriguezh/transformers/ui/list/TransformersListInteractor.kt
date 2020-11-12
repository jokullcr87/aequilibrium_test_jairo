package com.jrodriguezh.transformers.ui.list

import com.jrodriguezh.transformers.data.Transformer
import com.jrodriguezh.transformers.io.AllSparkService
import com.jrodriguezh.viperarch.contract.InteractorContract
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class TransformersListInteractor(private val allSparkService: AllSparkService?) : InteractorContract {

    fun getTransformers(): List<Transformer> {
        return emptyList()
    }

    fun token(onComplete: (String) -> Unit) {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            allSparkService!!.allSpark().body()?.let {
                onComplete(it)
            }
        }
    }
}