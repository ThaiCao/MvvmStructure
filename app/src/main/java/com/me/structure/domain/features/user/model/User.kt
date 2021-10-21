package com.me.structure.domain.features.user.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.me.structure.bases.BaseModel
import com.me.structure.domain.features.main.model.base.Dto
import com.me.structure.domain.features.main.model.base.Model
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class User(
    @SerializedName("id")
    val id: String? ="",
    @SerializedName("name")
    val name: String? ="",
    @SerializedName("email")
    val email: String? ="",
    @SerializedName("photo")
    val photo: String? =""
): BaseModel(), Parcelable, Model{
    override fun toLocalDto(): Dto {
        TODO("Not yet implemented")
    }

    override fun toRemoteDto(): Dto {
        TODO("Not yet implemented")
    }

}
