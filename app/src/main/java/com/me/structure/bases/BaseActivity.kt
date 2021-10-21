package com.me.structure.bases

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.me.structure.utils.common.LanguageUtility
import java.util.*

open class BaseActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        LanguageUtility.init(this)

    }

    open fun changeLanguage(locale: Locale?) {
        locale?.let { LanguageUtility.setDefaultLanguage(it, this) }
    }
}