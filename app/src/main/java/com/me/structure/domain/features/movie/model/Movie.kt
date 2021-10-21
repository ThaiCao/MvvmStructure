package com.me.structure.domain.features.movie.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.me.structure.domain.features.main.model.base.Dto
import com.me.structure.domain.features.main.model.base.Model
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class Movie (
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
): Parcelable, Model {
    override fun toLocalDto(): Dto {
        TODO("Not yet implemented")
    }

    override fun toRemoteDto(): Dto {
        TODO("Not yet implemented")
    }

}