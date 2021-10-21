package com.me.structure.domain.features.movie.usecase

import com.me.structure.domain.features.movie.repository.MovieRepository
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody

class GetMovieDetailUseCase(private val repository: MovieRepository) {
    operator fun invoke(id: String) : Single<ResponseBody> {
        return repository.getMovieDetail(id)
    }
}