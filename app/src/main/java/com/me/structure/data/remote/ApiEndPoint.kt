package com.me.structure.data.remote

object ApiEndPoint {
    const val EXCHANGE_TOKEN = "auth/exchangeToken"
    const val REFRESH_TOKEN = "auth/refreshToken"

    const val UPDATE_USER = "user/update"
    const val LOG_OUT_USER = "user/logout"
    const val LOG_IN_USER = "user/login"
    const val GET_MOVIES = "movie/popular"
    const val GET_MOVIE_DETAIL = "movie/{movieId}"
}