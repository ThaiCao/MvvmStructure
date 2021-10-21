package com.me.structure.domain.features.movie.usecase

import com.me.structure.data.remote.features.movie.model.MovieResponseDto
import com.me.structure.domain.features.movie.repository.MovieRepository
import io.reactivex.rxjava3.core.Single

class GetListMovieUseCase(private val repository: MovieRepository) {
    operator fun invoke(): Single<MovieResponseDto>{
        return repository.getListMovie()
    }
}