package com.me.structure.utils.common

import android.content.Context
import android.util.Patterns
import com.me.structure.R

object TextContentUtils {
    fun getTextValidateEmail(emailInput: String, context: Context): String? {
        return when {
            emailInput.isEmpty() -> {
                context.resources.getString(R.string.email_required_text)
            }
            !Patterns.EMAIL_ADDRESS.matcher(emailInput).matches() -> {
                context.resources.getString(R.string.email_invalid_text)
            }
            else -> {
                null

            }
        }
    }

    fun getTextValidatePassword(passwordInput: String, context: Context): String? {
        return when {
            passwordInput.isEmpty() -> {
                context.resources.getString(R.string.password_required_text)
            }
            else -> {
                null

            }
        }
    }
}