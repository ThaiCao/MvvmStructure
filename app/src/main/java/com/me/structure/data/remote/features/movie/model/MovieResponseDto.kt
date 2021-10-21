package com.me.structure.data.remote.features.movie.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.me.structure.domain.features.main.model.base.Dto
import com.me.structure.domain.features.main.model.base.Model
import com.me.structure.domain.features.movie.model.Movie
import com.me.structure.domain.features.movie.model.MovieResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class MovieResponseDto(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("results")
    val results: List<MovieDto>? = listOf(),
): Parcelable, Dto{
    override fun mapToDomainModel(): Model {
        return MovieResponse(
            page = page,
            totalResults = totalResults,
            totalPages = totalPages,
            results = results?.map{it.mapToDomainModel() as Movie}
        )
    }

}