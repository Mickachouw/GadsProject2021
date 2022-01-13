package com.mickachouw.gadsproject2021.data.repository.models

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asset_database")
data class Asset(
    @field:PrimaryKey
    @ColumnInfo(name = "assetId")
    @SerializedName("asset_id")
    val assetId: String,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,
    @ColumnInfo(name = "typeIsCrypto")
    @SerializedName("type_is_crypto")
    val typeIsCrypto: Int,
    @ColumnInfo(name = "idIcon")
    @SerializedName("id_icon")
    val idIcon: String,
    @ColumnInfo(name = "dataQuoteStart")
    @SerializedName("data_quote_start")
    val dataQuoteStart: String,
    @ColumnInfo(name = "dataQuoteEnd")
    @SerializedName("data_quote_end")
    val dataQuoteEnd: String,
    @ColumnInfo(name = "priceUsd")
    @SerializedName("price_usd")
    val priceUsd: Double,
)