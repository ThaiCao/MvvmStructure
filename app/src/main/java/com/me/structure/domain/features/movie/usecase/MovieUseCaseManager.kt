package com.me.structure.domain.features.movie.usecase

class MovieUseCaseManager(
    val getListMovieUseCase: GetListMovieUseCase,
    val getMovieDetailUseCase: GetMovieDetailUseCase
)