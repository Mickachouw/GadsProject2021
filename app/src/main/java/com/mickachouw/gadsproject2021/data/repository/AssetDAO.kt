
package com.mickachouw.gadsproject2021.data.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mickachouw.gadsproject2021.data.repository.models.Asset
//import kotlinx.coroutines.flow.Flow

@Dao
interface AssetDAO {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAsset(asset: Asset) : Long

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAssets(asset: List<Asset>) : Array<Long>

    @Query("SELECT * FROM asset_database WHERE assetId LIKE :assetID")
    fun findByAssetID(assetID: String): Asset

    @Query("SELECT * FROM asset_database")
    fun getAll(): List<Asset>

    @Delete (entity = Asset::class)
    suspend fun deleteAll(asset: Asset)

    @Query ("SELECT * FROM asset_database ORDER BY name ASC")
    fun getAssetList() : LiveData<List<Asset>>
}