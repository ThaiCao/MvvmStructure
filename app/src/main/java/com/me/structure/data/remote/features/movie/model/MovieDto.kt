package com.me.structure.data.remote.features.movie.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.me.structure.domain.features.main.model.base.Dto
import com.me.structure.domain.features.main.model.base.Model
import com.me.structure.domain.features.movie.model.Movie
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class MovieDto (
    @SerializedName("id")
    val id: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("release_date")
    val releaseDate: String?
): Parcelable, Dto {
    override fun mapToDomainModel(): Model {
        return Movie(
            id = id,
            posterPath = posterPath,
            backdropPath = backdropPath,
            title = title,
            voteAverage = voteAverage,
            voteCount = voteCount,
            releaseDate = releaseDate
        )
    }
}