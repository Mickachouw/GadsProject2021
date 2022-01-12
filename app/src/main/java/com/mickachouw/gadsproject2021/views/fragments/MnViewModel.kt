package com.mickachouw.gadsproject2021.views.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mickachouw.gadsproject2021.data.remote.RetrofitClient.apiService
import com.mickachouw.gadsproject2021.data.repository.AssetRoomDatabase
import com.mickachouw.gadsproject2021.data.repository.models.Asset
import kotlinx.coroutines.launch

class MnViewModel(application: Application) : AndroidViewModel(application) {

    private val assetDAO = AssetRoomDatabase.getDatabase(application, viewModelScope).assetDao()

    // LiveData which will be observed by UI class (Activity) for new Quote
    private val liveAsset = MutableLiveData<Asset>()

    init {
        // fetches Assets when ViewModel object is create
        getAllAssetsAndSaveIntoRoom()
    }

    private fun getAllAssetsAndSaveIntoRoom() {
        viewModelScope.launch {
            // fetch Assets from REST API
            val assets = apiService.getAllAssets()

            // save Assets in db
            assetDAO.insertAssets(assets)

            // publish Assets to LiveData
            for (asset in assets) {
                liveAsset.postValue(asset)
            }
        }
    }
}