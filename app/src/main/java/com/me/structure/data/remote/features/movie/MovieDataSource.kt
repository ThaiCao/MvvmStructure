package com.me.structure.data.remote.features.movie

import com.me.structure.BuildConfig

class MovieDataSource(private val movieServiceApi: IMovieServiceApi) {
    fun getListMovie() = movieServiceApi.getListMovie(
        BuildConfig.MOVIE_API_KEY)

    fun getMovieDetail(id: String) = movieServiceApi.getMovieDetail(BuildConfig.MOVIE_API_KEY,id)
}