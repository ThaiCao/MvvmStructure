package com.me.structure.presentation.features.home.controller

import com.airbnb.epoxy.EpoxyController
import com.me.structure.domain.features.movie.model.Movie
import com.me.structure.presentation.features.home.listener.OnMovieListener
import com.me.structure.presentation.features.home.model.ItemMovieModel_

class MovieController(private val onMovieListener: OnMovieListener) : EpoxyController() {

    private var data = mutableListOf<Movie>()

    override fun buildModels() {
        if (!data.isNullOrEmpty()) {
            data.mapIndexed { index, movie ->
                ItemMovieModel_()
                    .id(movie.id)
                    .listener(onMovieListener)
                    .movie(movie)
                    .addTo(this)
            }
        }
    }

    fun addMoreData(data: List<Movie>) {
        this.data.addAll(data)
        requestModelBuild()
    }

    fun refresh(data: List<Movie>) {
        this.data.clear()
        requestModelBuild()
        this.data.addAll(data)
        requestModelBuild()
    }
}