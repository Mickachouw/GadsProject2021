package com.mickachouw.gadsproject2021.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mickachouw.gadsproject2021.data.repository.models.Asset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//Annotates class to be a Room Database with a table (entity) of the Asset class
@Database(entities = [Asset::class], version = 1, exportSchema = false)
abstract class AssetRoomDatabase : RoomDatabase() {

    abstract fun assetDao(): AssetDAO

    private class AssetDataBaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.assetDao())
                }
            }
        }

        suspend fun populateDatabase(assetDao: AssetDAO) {

            // Add sample assets.
            var asset = Asset(
                "ETH",
                "Ethereum",
                1,
                "604ae453-3d9f-4ad0-9a48-9905cce617c2",
                "2015-08-07T14:50:38.1774950Z",
                "2021-12-14T08:56:54.1861721Z",
                3815.5115719010767324697922687,
            )
            assetDao.insertAsset(asset)
            asset = Asset(
                "BTC",
                "Bitcoin",
                1,
                "4caf2b16-a017-4e26-a348-2cea69c34cba",
                "2014-02-24T17:43:05.0000000Z",
                "2021-12-14T08:56:54.2826663Z",
                47466.039251039700393391890816,
            )
            assetDao.insertAsset(asset)
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: AssetRoomDatabase? = null

        //added a CoroutineScope
        fun getDatabase(context: Context, scope: CoroutineScope): AssetRoomDatabase {
            //return the INSTANCE if not null else create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AssetRoomDatabase::class.java,
                    "asset_database"
                ).addCallback(AssetDataBaseCallback(scope))
                    .build()
                INSTANCE = instance
                //return instance
                return instance
            }
        }
    }

}
