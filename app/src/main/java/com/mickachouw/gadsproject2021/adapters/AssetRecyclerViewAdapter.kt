package com.mickachouw.gadsproject2021.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.mickachouw.gadsproject2021.R
import com.mickachouw.gadsproject2021.data.repository.models.Asset
import com.mickachouw.gadsproject2021.databinding.FragmentItemBinding
import android.content.Context
import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
import com.mickachouw.gadsproject2021.data.remote.NetworkUtils
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import coil.transform.RoundedCornersTransformation
import com.mickachouw.gadsproject2021.views.fragments.ListAssetFragmentDirections


/**
 * [RecyclerView.Adapter] that can display an [Asset].
 */
class AssetRecyclerViewAdapter(
    private val assetList: List<Asset>,
    private val context: Context
) : RecyclerView.Adapter<AssetRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(assetList[position], position)
    }

    override fun getItemCount(): Int = assetList.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(current: Asset, position: Int) {
            val cardView: CardView = itemView.findViewById(R.id.card_view)
            val assetName: TextView = itemView.findViewById(R.id.asset_name)
            val assetSigle: TextView = itemView.findViewById(R.id.asset_sigle)
            val assetValue: TextView = itemView.findViewById(R.id.asset_value)
            val assetImage: ImageView = itemView.findViewById(R.id.assetImageView)

            current.let {
                assetName.text = current.name
                assetSigle.text = current.assetId
                assetValue.text = current.priceUsd.toString().split(".")[0]
                assetImage.load(
                    "${NetworkUtils.URL_IMAGE}/${
                        current.idIcon.split('-').joinToString(separator = "")
                    }.png"
                ) {
                    crossfade(true)
                    transformations(CircleCropTransformation(), RoundedCornersTransformation())
                }
                cardView.setOnClickListener() {
//                    val navHostFragment =
//                        supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
//                    val navController = navHostFragment.navController
                    val action =
                        ListAssetFragmentDirections
                            .actionListItemToAssetDetail(current.assetId)
                    it?.findNavController()?.navigate(action)
                }
            }
        }

    }

    companion object {
        private val TAG = AssetRecyclerViewAdapter::class.java.simpleName
    }
}

