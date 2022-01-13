package com.mickachouw.gadsproject2021.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.mickachouw.gadsproject2021.R
import com.mickachouw.gadsproject2021.adapters.AssetRecyclerViewAdapter
import com.mickachouw.gadsproject2021.data.repository.AssetRoomDatabase
import com.mickachouw.gadsproject2021.data.repository.models.Asset
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
class ListAssetFragment : Fragment() {

    private lateinit var listAsset: List<Asset>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        lifecycleScope.launch(Dispatchers.IO) {
            val assetDAO =
                AssetRoomDatabase.getDatabase(requireContext(), MainScope()).assetDao()
            listAsset = assetDAO.getAll()
        }
        listAsset = listOf(
            Asset(
                "ETH",
                "Ethereum",
                1,
                "604ae453-3d9f-4ad0-9a48-9905cce617c2",
                "2015-08-07T14:50:38.1774950Z",
                "2021-12-14T08:56:54.1861721Z",
                3815.5115719010767324697922687,
            ),
            Asset(
                "BTC",
                "Bitcoin",
                1,
                "4caf2b16-a017-4e26-a348-2cea69c34cba",
                "2014-02-24T17:43:05.0000000Z",
                "2021-12-14T08:56:54.2826663Z",
                47466.039251039700393391890816,
            )
        )
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.list_item_recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = AssetRecyclerViewAdapter(listAsset, requireContext())
        return view
    }
}