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
        // TODO
//        val asset: Asset = List<Asset>().getAsset(assetID)
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
//            val cryptoVariation : TextView = itemView.findViewById(R.id.crypto_variation)

        assetName.text = asset.name
        assetSigle.text = asset.assetId
        assetValue.text = asset.priceUsd.toString().split(".")[0]
        if (asset.idIcon != null) {
            assetImage.load(
                "${NetworkUtils.URL_IMAGE}/${
                    asset.idIcon.split('-').joinToString(separator = "")
                }.png"
            )
        }
        return view
    }
}


//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment AssetDetailFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            AssetDetailFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
