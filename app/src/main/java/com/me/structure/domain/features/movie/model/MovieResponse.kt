package com.me.structure.domain.features.movie.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.me.structure.domain.features.main.model.base.Dto
import com.me.structure.domain.features.main.model.base.Model
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class MovieResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("results")
    val results: List<Movie>? = listOf(),
) : Parcelable, Model {
    override fun toLocalDto(): Dto {
        TODO("Not yet implemented")
    }

    override fun toRemoteDto(): Dto {
        TODO("Not yet implemented")
    }

}