package com.me.structure.utils

import java.util.regex.Pattern

class AppConstConfig {
    companion object {
        const val SERVER_AUTHEN_URL = "https://authen-api/"
        const val SERVER_USER_URL = "https://user-api"
        const val SERVER_MOVIE_URL = "https://movie-api/"
        const val DELAY_TIME = 500L
        val PASSWORD_PATTERN: Pattern =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\\\S+\$).{8,}\$\n")
    }
}