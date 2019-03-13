package com.example.lab3.utils

import android.content.Context
import android.preference.PreferenceManager

object PreferenceUtils {

    private const val PREF_LOGGED = "pref_logged"
    private const val PREF_EMAIL = "pref_email"

    private fun getPreferences(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveLogged(context: Context, isLogged: Boolean) {
        getPreferences(context).edit().putBoolean(PREF_LOGGED, isLogged).apply()
    }

    fun getLogged(context: Context) = getPreferences(context).getBoolean(PREF_LOGGED, false)

    fun saveCurrentEmail(context: Context, email: String) {
        getPreferences(context).edit().putString(PREF_EMAIL, email).apply()
    }

    fun getCurrentEmail(context: Context) = getPreferences(context).getString(PREF_EMAIL, "")

}