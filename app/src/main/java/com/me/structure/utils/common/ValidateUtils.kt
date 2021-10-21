package com.me.structure.utils.common

import android.util.Patterns

object ValidateUtils {

    fun isValidateEmail(emailInput: String): Boolean {
        return when {
            emailInput.isEmpty() -> {
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(emailInput).matches() -> {
                false
            }
            else -> {
                true
            }
        }
    }

    fun isValidatePassword(passwordInput: String): Boolean {
        return when {
            passwordInput.isEmpty() -> {
                false
            }
            else -> {
                true
            }
        }
    }
}