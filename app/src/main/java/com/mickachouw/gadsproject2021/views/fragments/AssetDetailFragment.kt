package com.mickachouw.gadsproject2021.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.mickachouw.gadsproject2021.R
import com.mickachouw.gadsproject2021.data.remote.NetworkUtils
import com.mickachouw.gadsproject2021.data.repository.models.Asset

class AssetDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val assetID = arguments?.getString("assetID")
        val asset =
            Asset(
                "ETH",
                "Ethereum",
                1,
                "604ae453-3d9f-4ad0-9a48-9905cce617c2",
                "2015-08-07T14:50:38.1774950Z",
                "2021-12-14T08:56:54.1861721Z",
                3815.5115719010767324697922687,
            )

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_asset_detail, container, false)
        val assetName: TextView = view.findViewById(R.id.asset_name)
        val assetSigle: TextView = view.findViewById(R.id.asset_sigle)
        val assetValue: TextView = view.findViewById(R.id.asset_value)
        val assetImage: ImageView = view.findViewById(R.id.assetImageView)

        assetName.text = asset.name
        assetSigle.text = asset.assetId
        assetValue.text = asset.priceUsd.toString().split(".")[0]
        assetImage.load(
            "${NetworkUtils.URL_IMAGE}/${
                asset.idIcon.split('-').joinToString(separator = "")
            }.png"
        )
        return view
    }
}