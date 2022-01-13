package com.mickachouw.gadsproject2021.views.activities

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import coil.load
import com.mickachouw.gadsproject2021.R
import com.mickachouw.gadsproject2021.data.remote.NetworkUtils
import com.mickachouw.gadsproject2021.data.remote.RetrofitClient
import com.mickachouw.gadsproject2021.data.repository.AssetRoomDatabase
import com.mickachouw.gadsproject2021.databinding.ActivityMainBinding
import com.mickachouw.gadsproject2021.views.fragments.MainViewModel
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private var job: Job? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        val viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getAllAssets()
        // observe quote from view-model and bind it to UI widgets
        viewModel.liveAsset.observe(this, Observer {
            // Inflate the layout for this fragment
            val assetName: TextView = binding.root.findViewById(R.id.asset_name)
            val assetSigle: TextView = binding.root.findViewById(R.id.asset_sigle)
            val assetValue: TextView = binding.root.findViewById(R.id.asset_value)
            val assetImage: ImageView = binding.root.findViewById(R.id.assetImageView)

            assetName.text = it.name
            assetSigle.text = it.assetId
            assetValue.text = it.priceUsd.toString().split(".")[0]
            if (it.idIcon != null) {
                assetImage.load(
                    "${NetworkUtils.URL_IMAGE}/${
                        it.idIcon.split('-').joinToString(separator = "")
                    }.png"
                )
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return (Navigation.findNavController(this, R.id.navHostFragment).navigateUp()
                || super.onSupportNavigateUp())
    }


    private fun getAllAssets() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.apiService.getAllAssets()
            withContext(Dispatchers.Main) {
                if (response.isNotEmpty()) {
                    val listAsset = response
                }

            }
            val db = AssetRoomDatabase.getDatabase(this@MainActivity, MainScope())
            for (asset in response) db.assetDao().insertAsset(asset)
            db.close()
        }
    }

    fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, msg, duration).show()
    }
}
