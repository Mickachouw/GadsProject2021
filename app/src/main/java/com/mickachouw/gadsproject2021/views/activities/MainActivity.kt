package com.mickachouw.gadsproject2021.views.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.mickachouw.gadsproject2021.R
import com.mickachouw.gadsproject2021.data.remote.APIService
import com.mickachouw.gadsproject2021.data.remote.RetrofitClient
import com.mickachouw.gadsproject2021.data.repository.AssetRoomDatabase
import com.mickachouw.gadsproject2021.data.repository.models.Asset
import com.mickachouw.gadsproject2021.databinding.ActivityMainBinding
import com.mickachouw.gadsproject2021.views.fragments.AssetRecyclerViewAdapter
import com.mickachouw.gadsproject2021.views.fragments.MnViewModel
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class MainActivity : AppCompatActivity() {
//    var job: Job? = null
    private var binding: ActivityMainBinding? = null
//    private var browsed = MutableLiveData<List<Asset>>()
//    val loading = MutableLiveData<Boolean>()
    private val apiService: APIService by lazy {
        RetrofitClient.apiService
    }
    private val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    private val viewModel = ViewModelProvider(this, factory)[MnViewModel::class.java]


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//Create top level destinations for the various home activities of the bottom nav view.
//        val appBarConfiguration = AppBarConfiguration.Builder(
//            R.id.overviewFragment,
//            R.id.categoryFragment,
//            R.id.searchFragment
//        ).build()

        //Find the nav controller.
        val navController = Navigation.findNavController(this, R.id.navHostFragment)

        //Setup the BottomNavView with the nav controller
//        NavigationUI.setupWithNavController(binding!!.bottomNavView, navController)

        //Setup the appbar/toolbar with the nav controller.
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
//        getAllAssets()

        nextBtn.setOnClickListener { viewModel.getAllAss}

        // observe quote from view-model and bind it to UI widgets
        viewModel.liveQuote.observe(this, Observer {
            quoteText.text = it.text
            author.text = it.autor
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return (Navigation.findNavController(this, R.id.navHostFragment).navigateUp()
                || super.onSupportNavigateUp())
    }

//    private fun getAllAssets() {
//        job = CoroutineScope(Dispatchers.IO).launch {
//            val response = RetrofitClient.apiService.getAllAssets()
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    val listAsset = response.body()!!
//                    browsed.postValue(response.body())
//                    val db = AssetRoomDatabase.getDatabase(this@MainActivity, MainScope())
//                    for (asset in listAsset) db.assetDao().insertAsset(asset)
//                    db.close()
//                    loading.value = false
////                } else {
////                   Log("Error : ${response.message()} ")
////                }
//            }
//
//        }
//
//    }

//    override fun onBackPressed() {
//        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) closeDrawer()
//        else {
//            super.onBackPressed()
//            //mRealm!!.close()
//        }
//    }



    fun setupRecyclerView(items: List<Asset>) {
        val assetRecyclerViewAdapter = AssetRecyclerViewAdapter(items, applicationContext)

    }

    fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, msg, duration).show()
    }

//    fun Context.showErrorMessage(errorBody: ResponseBody, duration: Int = Toast.LENGTH_SHORT) {
//        val gson = GsonBuilder().create()
//        try {
//            val errorResponse = gson.fromJson(errorBody.string(), ErrorResponse::class.java)
//          toast(errorResponse.message!!, duration)
//        } catch (e: IOException) {
//            Log.i("Exception ", e.toString())
//        }
//    }

//    data class ErrorResponse(val message: String?)

}
//    companion object {
//        val TAG = MainActivity::class.simpleName
//    }
