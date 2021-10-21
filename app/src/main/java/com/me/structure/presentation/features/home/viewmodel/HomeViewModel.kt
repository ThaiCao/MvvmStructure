package com.me.structure.presentation.features.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.me.structure.bases.BaseViewModel
import com.me.structure.bases.addTo
import com.me.structure.domain.features.movie.model.Movie
import com.me.structure.domain.features.movie.model.MovieResponse
import com.me.structure.domain.features.movie.usecase.MovieUseCaseManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(private val movieUseCaseManager: MovieUseCaseManager): BaseViewModel() {

    var listMovie = MutableLiveData<List<Movie>>()

    fun getListMovie(){
        movieUseCaseManager.getListMovieUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    android.util.Log.e("TEST_DATA","getListMovie SUCESSS")
                    listMovie.value = (it.mapToDomainModel() as MovieResponse).results!!
                },
                {
                    android.util.Log.e("TEST_DATA","getListMovie Exception= ${it.toString()}")
                }
            )
            .addTo(compositeDisposables)
    }
}