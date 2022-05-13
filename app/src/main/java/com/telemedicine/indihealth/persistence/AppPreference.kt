package com.telemedicine.indihealth.persistence

import android.content.Context
import androidx.preference.PreferenceManager

class AppPreference(private val context: Context) {
    companion object {
        private val ANY_KEY = "any_key"
        const val PREF_KEY_USERNAME = "username"
        const val PREF_KEY_PASSWORD = "password"
        const val PREF_JWT_TOKEN = "jwt-token"
        const val URL_LOGO = "url-logo"
    }

    fun saveString(key: String, value: String) {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)

        val editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        return pref.getString(key, "")!!
    }

    fun saveBoolean(key: String, value: Boolean) {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)

        val editor = pref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        return pref.getBoolean(key, false)
    }
}