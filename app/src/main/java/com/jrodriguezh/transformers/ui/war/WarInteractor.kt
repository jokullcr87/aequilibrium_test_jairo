package com.jrodriguezh.transformers.ui.war

import android.content.SharedPreferences
import com.jrodriguezh.transformers.data.Transformer
import com.jrodriguezh.transformers.io.AllSparkService
import com.jrodriguezh.transformers.io.TransformersService
import com.jrodriguezh.viperarch.contract.InteractorContract
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class WarInteractor(private val transformersService: TransformersService,
                    private val prefs: SharedPreferences) : InteractorContract {

    private fun getTokenFromPreferences() : String? {
        return prefs.getString("bearer_token", null)
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