package com.me.structure.utils.common

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import com.me.structure.data.local.sharedpreferences.PrefKeys.COUNTRY
import com.me.structure.data.local.sharedpreferences.PrefKeys.LANGUAGE
import com.me.structure.data.local.sharedpreferences.PrefKeys.VARIANT
import com.me.structure.sharedPrefs
import java.util.*

object LanguageUtility {
    private var configuration: Configuration? = null
    fun setDefaultLanguage(locale: Locale, context: Context) {
        Locale.setDefault(locale)
        sharedPrefs.saveData(LANGUAGE, locale.language)
        sharedPrefs.saveData(COUNTRY, locale.country)
        sharedPrefs.saveData(VARIANT, locale.variant)
        configuration = createConfiguration(context)
    }

    /**
     * Used to update your app context in case you cache it.
     */
    fun createConfigurationContext(context: Context): Context {
        return context.createConfigurationContext(getConfiguration(context))
    }

    fun init(activity: Activity) {
        activity.applyOverrideConfiguration(getConfiguration(activity.applicationContext))
        // you can't access sharedPrefferences from activity constructor
        // with activity context, so I used the app context.
        Locale.setDefault(getLocale(activity.applicationContext))
    }

    private fun getConfiguration(context: Context): Configuration {
        if (configuration == null) {
            configuration = createConfiguration(context)
        }
        return configuration!!
    }

    private fun createConfiguration(context: Context): Configuration {
        val locale = getLocale(context)
        val configuration = Configuration()
        configuration.setLocale(locale)
        LanguageUtility.configuration = configuration
        return configuration
    }

    private fun getLocale(context: Context): Locale {
        val aDefault = Locale.getDefault()
        val language = sharedPrefs.getStringData(LANGUAGE, aDefault.language)
        val country = sharedPrefs.getStringData(COUNTRY, aDefault.country)
        val variant = sharedPrefs.getStringData(VARIANT, aDefault.variant)
        return Locale(language, country, variant)
    }
}