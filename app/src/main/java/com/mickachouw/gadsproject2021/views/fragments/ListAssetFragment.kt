package com.mickachouw.gadsproject2021.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mickachouw.gadsproject2021.R
import com.mickachouw.gadsproject2021.data.repository.models.Asset
import com.mickachouw.gadsproject2021.views.fragments.placeholder.AssetContent

/**
 * A fragment representing a list of Items.
 */
class ListAssetFragment (listAsset: List<Asset>) : Fragment() {

//    private val recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        val recyclerView : RecyclerView = view.findViewById(R.id.list_item_recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = AssetRecyclerViewAdapter(Asset, context)

        // Set the adapter
//        if (view is RecyclerView) {
//            with(view) {
//                layoutManager = when {
//                    columnCount <= 1 -> LinearLayoutManager(context)
//                    else -> GridLayoutManager(context, columnCount)
//                }
////                adapter = AssetRecyclerViewAdapter(AssetContent.ITEMS, context)
//                adapter = AssetRecyclerViewAdapter(List<Asset>, context)
//            }
//        }
        return view
    }

//    companion object {
//
//        // TODO: Customize parameter argument names
//        const val ARG_COLUMN_COUNT = "column-count"
//
//        // TODO: Customize parameter initialization
//        @JvmStatic
//        fun newInstance(columnCount: Int) =
//            ListAssetFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_COLUMN_COUNT, columnCount)
//                }
//            }
//    }
}