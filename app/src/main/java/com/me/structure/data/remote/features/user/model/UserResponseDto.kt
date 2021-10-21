package com.me.structure.data.remote.features.user.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.me.structure.domain.features.main.model.base.Dto
import com.me.structure.domain.features.main.model.base.Model
import com.me.structure.domain.features.user.model.User
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class UserResponseDto (
    @SerializedName("id")
    val id: String? ="",
    @SerializedName("name")
    val name: String? ="",
    @SerializedName("email")
    val email: String? ="",
    @SerializedName("photo")
    val photo: String? =""
) : Parcelable, Dto {
    override fun mapToDomainModel(): Model {
        return User(
            id = id,
            name = name,
            email = email,
            photo = photo,
        )
    }

}