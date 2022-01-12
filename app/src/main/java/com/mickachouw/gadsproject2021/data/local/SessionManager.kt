package com.mickachouw.gadsproject2021.data.local

import android.content.Context
import android.content.SharedPreferences

// TODO : SharedPreferences
class SessionManager(var context: Context) {
    var prefs: SharedPreferences
    var editor: SharedPreferences.Editor
    var PRIVATE_MODE = 0

    /**
     * ============================================================================
     * KEEP ALL SESSION GETTERS BELOW THIS LINE
     * ============================================================================
     */
    fun GET_FILTER_CATEGORY(): String? {
        return prefs.getString(KEY_FILTER_CATEGORY, "")
    }

    fun GET_FILTER_SUB_CATEGORY(): String? {
        return prefs.getString(KEY_FILTER_SUB_CATEGORY, "")
    }

    fun GET_SELECTED_ELEMENT(): String? {
        return prefs.getString(KEY_SELECTED_ELEMENT, "")
    }

    /**
     * ============================================================================
     * KEEP ALL SESSION SETTERS BELOW THIS LINE
     * ============================================================================
     */
    fun SET_FILTER_CATEGORY(value: String?) {
        editor.putString(KEY_FILTER_CATEGORY, value).commit()
    }

    fun SET_FILTER_SUB_CATEGORY(value: String?) {
        editor.putString(KEY_FILTER_SUB_CATEGORY, value).commit()
    }

    fun SET_SELECTED_ELEMENT(value: String?) {
        editor.putString(KEY_SELECTED_ELEMENT, value).commit()
    }

    companion object {
        private const val KEY_FILTER_CATEGORY = "filter_category"
        private const val KEY_FILTER_SUB_CATEGORY = "filter_sub_category"
        private const val KEY_SELECTED_ELEMENT = "selected_element"
        private const val PREF_NAME = "TheElements Preferences"
    }

    init {
        prefs = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = prefs.edit()
    }
}