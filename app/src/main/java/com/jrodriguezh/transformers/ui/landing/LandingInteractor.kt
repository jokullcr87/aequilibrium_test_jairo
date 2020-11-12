package com.jrodriguezh.transformers.ui.landing

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


class LandingInteractor(private val allSparkService: AllSparkService,
                        private val transformersService: TransformersService,
                        private val prefs: SharedPreferences) : InteractorContract {

    var myToken: String? = null

    fun getTransformers(onComplete: (List<Transformer>) -> Unit, onError: (Throwable?) -> Unit) {
        val list = CoroutineScope(Job() + Dispatchers.Main).async {
            var list: List<Transformer> = emptyList()
            myToken?.let { token->
                transformersService.transformers("Bearer $token").body()?.let { list = it.transformers }
            }
            list
        }

        with(list) {
            invokeOnCompletion {
                if (it != null)
                    onError(it)
                else
                    onComplete(getCompleted())
            }
        }
    }

    fun checkToken(): Boolean {
        return storedToken() != null
    }

    fun storedToken(): String? = getTokenFromPreferences().let {
            myToken = it
            myToken
        }


    fun saveToken(token: String) {
        prefs.edit().putString("bearer_token", token).apply()
    }

    fun token(onComplete: (String) -> Unit, onError: (Throwable?) -> Unit) {
        val token = CoroutineScope(Job() + Dispatchers.Main).async {
            var prefs: String? = null
            val prefToken = getTokenFromPreferences()
            if (prefToken.isNullOrEmpty())
                getTokenFromService()?.let { prefs = it }
            else
                prefs = prefToken

            prefs
        }

        with(token) {
            invokeOnCompletion {
                if (it != null)
                    onError(it)
                else
                    this.getCompleted()!!.let { response ->
                        myToken = response
                        onComplete(response)
                    }
            }
        }
    }

    private fun getTokenFromPreferences() : String? {
        return prefs.getString("bearer_token", null)
    }

    private suspend fun getTokenFromService() : String? {
        return allSparkService.allSpark().body()
    }
}