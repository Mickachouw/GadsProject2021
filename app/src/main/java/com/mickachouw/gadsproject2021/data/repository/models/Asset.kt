package com.mickachouw.gadsproject2021.data.repository.models

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import androidx.room.Entity
import androidx.room.PrimaryKey
//import java.util.*

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
//    @SerializedName("data_end")
//    val dataEnd: String,
//    @SerializedName("data_orderbook_end")
//    val dataOrderbookEnd: String,
//    @SerializedName("data_orderbook_start")
//    val dataOrderbookStart: String,
//    @SerializedName("data_start")
//    val dataStart: String,
//    @SerializedName("data_symbols_count")
//    val dataSymbolsCount: Int,
//    @SerializedName("data_trade_end")
//    val dataTradeEnd: String,
//    @SerializedName("data_trade_start")
//    val dataTradeStart: String,
//    @SerializedName("volume_1day_usd")
//    val volume1dayUsd: Double,
//    @SerializedName("volume_1hrs_usd")
//    val volume1hrsUsd: Double,
//    @SerializedName("volume_1mth_usd")
//    val volume1mthUsd: Double
)