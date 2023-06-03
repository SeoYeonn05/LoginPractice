package com.example.loginsignuppractice.util

import android.content.Context
import android.content.SharedPreferences


class SharedPreferenceUtil() {
    private val PREF_NAME = "MyPrefs"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }


    fun saveString(context: Context, key: String, value: String) {
        var pref: SharedPreferences = getPreferences(context)
        pref.edit().putString(key, value).apply()
    }

    fun getString(context: Context,key: String, defaultValue: String): String {
        var pref: SharedPreferences = getPreferences(context)

        return pref.getString(key, defaultValue) ?: defaultValue
    }

    fun saveInt(context: Context,key: String, value: Int) {
        var pref: SharedPreferences = getPreferences(context)

        pref.edit().putInt(key, value).apply()
    }

    fun getInt(context: Context, key: String, defaultValue: Int): Int {
        var pref: SharedPreferences = getPreferences(context)

        return pref.getInt(key, defaultValue) ?: defaultValue
    }

    fun saveBoolean(context: Context,key: String, value: Boolean) {
        var pref: SharedPreferences = getPreferences(context)

        pref.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(context: Context,key: String, defaultValue: Boolean): Boolean {
        var pref: SharedPreferences = getPreferences(context)

        return pref.getBoolean(key, defaultValue) ?: defaultValue
    }

}