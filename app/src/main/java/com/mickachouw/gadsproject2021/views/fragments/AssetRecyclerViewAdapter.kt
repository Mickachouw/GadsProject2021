package com.mickachouw.gadsproject2021.views.fragments

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

/**
 * [RecyclerView.Adapter] that can display an [Asset].
 */
class AssetRecyclerViewAdapter(
    private val assetList: List<Asset>,
    private val context: Context,
) : RecyclerView.Adapter<AssetRecyclerViewAdapter.ViewHolder>() {

    private lateinit var binding: FragmentItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view : View = LayoutInflater.from(context).inflate(R.layout.fragment_item_list, parent, false))
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
            val assetName: TextView = itemView.findViewById(R.id.asset_name)
            val assetSigle: TextView = itemView.findViewById(R.id.asset_sigle)
            val assetValue: TextView = itemView.findViewById(R.id.asset_value)
            val assetImage: ImageView = itemView.findViewById(R.id.assetImageView)
//            val cryptoVariation : TextView = itemView.findViewById(R.id.crypto_variation)

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
                    transformations(CircleCropTransformation())
                }
            }
        }

    }

    companion object {
        private val TAG = AssetRecyclerViewAdapter::class.java.simpleName
    }
}

