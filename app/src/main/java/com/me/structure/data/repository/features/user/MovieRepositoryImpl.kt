package com.me.structure.data.repository.features.user

import com.me.structure.data.remote.features.movie.MovieDataSource
import com.me.structure.data.remote.features.movie.model.MovieResponseDto
import com.me.structure.domain.features.movie.repository.MovieRepository
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody

class MovieRepositoryImpl(
    private val movieDataSource: MovieDataSource
): MovieRepository {
    override fun getListMovie(): Single<MovieResponseDto> {
        return movieDataSource.getListMovie()
    }

    override fun getMovieDetail(id: String): Single<ResponseBody> {
        return movieDataSource.getMovieDetail(id)
    }

}