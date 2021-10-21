package com.me.structure.data.local.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.me.structure.domain.features.user.model.User

class SharedPrefUtils (private val context: Context) {

    companion object {
        const val PREF_APP = "prefs_app_data"
        const val USER_INFO = "USER_INFO"
    }

    fun getBooleanData(key: String?): Boolean {
        return context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE)
            .getBoolean(key, false)
    }

    fun getIntData(key: String?): Int {
        return context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE).getInt(key, 0)
    }


    fun getStringData(key: String?, defaultString: String? = ""): String {
        return context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE)
            .getString(key, defaultString)
            .toString()
    }


    fun saveData(key: String?, value: String?) {
        context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE).edit()
            .putString(key, value).apply()
    }

    fun clearData(key: String?) {
        context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE).edit()
            .remove(key).apply()
    }

    fun saveData(key: String?, value: Int) {
        context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE).edit().putInt(key, value)
            .apply()
    }

    fun saveData(key: String?, value: Boolean) {
        context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    fun getSharedPrefEditor(pref: String?): SharedPreferences.Editor {
        return context.getSharedPreferences(pref, Context.MODE_PRIVATE).edit()
    }

    fun saveData(editor: SharedPreferences.Editor) {
        editor.apply()
    }

    fun loadFirebaseToken(): String {
        return getStringData("FIREBASE_TOKEN", "")
    }

    fun saveUserInfo(accountRequest: User) {
        context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE).edit()
            .putString(USER_INFO, Gson().toJson(accountRequest)).apply()
    }


    fun getUserInfo(): User?{
        val json: String? = context.getSharedPreferences(PREF_APP, Context.MODE_PRIVATE)
            .getString(USER_INFO, "")
        return try {
            Gson().fromJson(json, User::class.java)
        } catch (e: java.lang.Exception) {
            null
        }
    }
}