package com.mickachouw.gadsproject2021.views.fragments

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.mickachouw.gadsproject2021.data.remote.APIService
import com.mickachouw.gadsproject2021.data.remote.RetrofitClient
import com.mickachouw.gadsproject2021.data.remote.RetrofitClient.apiService
import com.mickachouw.gadsproject2021.data.repository.AssetDAO
import com.mickachouw.gadsproject2021.data.repository.AssetRoomDatabase
import com.mickachouw.gadsproject2021.data.repository.models.Asset
import com.mickachouw.gadsproject2021.views.activities.MainActivity
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    private val browsed = MutableLiveData<List<Asset>>()
    private var job: Job? = null

//    private var browsed: List<Asset> = mutableListOf()

    private val loading = MutableLiveData<Boolean>()

    private fun getAllAssets() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.getAllAssets()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val listAsset = response.body()!!
                    browsed.postValue(response.body())
                    val db = AssetRoomDatabase.getDatabase(this@MainActivity, MainScope())
                    for (asset in listAsset) db.assetDao().insertAsset(asset)
                    db.close()
                    loading.value = false
                } else {
                   onError("Error : ${response.message()} ")
                }
                }

            }

        }

    private fun getAllAssetsAndSaveIntoRoom() {
        viewModelScope.launch {
            // fetch Assets from REST API
            val assets = apiService.getAllAssets()

            // save assets in db
            AssetDAO.insertAssets(assets)

            for (asset in assets) {
                AssetDAO.insertAsset()
            }
            // publish quote to LiveData
            liveQuote.postValue(quote)
        }
    }


    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
    fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, msg, duration).show()
    }

}