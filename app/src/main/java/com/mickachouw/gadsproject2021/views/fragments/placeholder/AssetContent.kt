package com.mickachouw.gadsproject2021.views.fragments.placeholder


import java.util.ArrayList
import java.util.HashMap
import com.mickachouw.gadsproject2021.data.repository.models.Asset

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object AssetContent {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<Asset> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    private val ITEM_MAP: MutableMap<String, Asset> = HashMap()

    private const val COUNT = 24

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
//            addItem(createAsset(i))
        }
    }

    private fun addItem(item: Asset) {
        ITEMS.add(item)
        ITEM_MAP[item.idIcon] = item
    }

//    private fun createAsset(position: Int): Asset {}
//        return Asset(position.toString(), "Item " + position, makeDetails(position))


    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0 until position) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A placeholder item representing a piece of content.
     */
//    data class PlaceholderItem(val id: String, val content: String, val details: String) {
//        override fun toString(): String = content
    }
