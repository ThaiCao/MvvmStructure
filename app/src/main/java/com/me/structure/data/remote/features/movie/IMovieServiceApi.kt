package com.me.structure.data.remote.features.movie

import com.me.structure.data.remote.ApiEndPoint
import com.me.structure.data.remote.features.movie.model.MovieResponseDto
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface IMovieServiceApi {

    @GET(ApiEndPoint.GET_MOVIES)
    fun getListMovie(@Query("api_key") apiKey: String): Single<MovieResponseDto>

    @GET(ApiEndPoint.GET_MOVIE_DETAIL)
    fun getMovieDetail(@Query("api_key") apiKey: String, id: String): Single<ResponseBody>
}