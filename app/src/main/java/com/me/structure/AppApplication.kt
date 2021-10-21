package com.me.structure

import android.app.Application
import com.me.structure.data.local.sharedpreferences.SharedPrefUtils
import com.me.structure.koin.components.applicationComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

val sharedPrefs: SharedPrefUtils by lazy { AppApplication.prefs!! }

class AppApplication : Application(){

    companion object {
        var prefs: SharedPrefUtils? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = SharedPrefUtils(context = applicationContext)

        startKoin {
            androidContext(this@AppApplication)
            modules(applicationComponent)
        }
    }
}