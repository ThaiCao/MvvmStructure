package com.me.structure.utils.common

import android.content.Context
import android.content.Intent
import com.me.structure.presentation.features.login.activities.LoginActivity
import com.me.structure.presentation.features.main.activities.MainActivity

object NavigationUtils {

    fun navigateToMainActivity(context: Context?) {
        context?.startActivity(MainActivity.newInstance(context).apply {
            addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
            )
        })
    }

    fun navigateToLoginActivity(context: Context?) {
        context?.startActivity(LoginActivity.newInstance(context).apply {
            addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
            )
        })
    }
}