package com.me.structure.domain.features.movie.repository

import com.me.structure.data.remote.features.movie.model.MovieResponseDto
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody

interface MovieRepository {
    fun getListMovie(): Single<MovieResponseDto>

    fun getMovieDetail(id: String): Single<ResponseBody>
}