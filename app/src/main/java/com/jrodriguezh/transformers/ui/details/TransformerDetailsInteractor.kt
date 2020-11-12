package com.jrodriguezh.transformers.ui.details

import android.content.SharedPreferences
import com.jrodriguezh.transformers.data.Transformer
import com.jrodriguezh.transformers.io.TransformersService
import com.jrodriguezh.viperarch.contract.InteractorContract
import kotlinx.coroutines.*
import javax.xml.transform.TransformerException

class TransformerDetailsInteractor(private val transformersService: TransformersService,
                                   private val prefs: SharedPreferences) : InteractorContract {

    private fun getTokenFromPreferences() : String? {
        return prefs.getString("bearer_token", null)
    }

    fun delete(id: String, onComplete:() -> Unit, onError: (Throwable) -> Unit) {
        val deleteJob = CoroutineScope(Job() + Dispatchers.Main).async {
            getTokenFromPreferences()?.let { token ->
                transformersService.deleteTransformer("Bearer $token", id)
            }
        }
        with(deleteJob) {
            invokeOnCompletion {
                if (it != null)
                    onError(it)
                else
                    onComplete()
            }
        }
    }

    fun saveDetails(data: Transformer, onComplete: (Transformer) -> Unit, onError: (Throwable) -> Unit) {
        val transformer = CoroutineScope(Job() + Dispatchers.Main).async {
            lateinit var result: Transformer
            if (data.id != null)
                getTokenFromPreferences()?.let { token ->
                    transformersService.updateTransformer("Bearer $token", data).body()?.let {
                        result = it
                    }
                }
            else
                getTokenFromPreferences()?.let { token ->
                    transformersService.saveTransformer("Bearer $token", data).body()?.let {
                        result = it
                    }
                }
            result
        }

        with(transformer) {
            invokeOnCompletion {
                if (it != null)
                    onError(it)
                else
                    this.getCompleted().let { response ->
                        onComplete(response)
                    }
            }
        }
    }

}