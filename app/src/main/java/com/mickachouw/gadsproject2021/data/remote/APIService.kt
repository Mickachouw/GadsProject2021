package com.mickachouw.gadsproject2021.data.remote

import com.mickachouw.gadsproject2021.data.repository.models.Asset
// import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface APIService {
    @Headers("X-CoinAPI-Key: ---")
    @GET("assets/")
    suspend fun getAllAssets(): List<Asset>

    @Headers("X-CoinAPI-Key: ---")
    @GET("assets/{asset_id}")
    suspend fun getAsset(@Path("asset_id") assetID: String?): Asset

}
