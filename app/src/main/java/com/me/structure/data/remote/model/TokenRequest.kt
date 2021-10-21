package com.me.structure.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TokenRequest(
    @SerializedName("token")
    val token: String? = ""
) : Parcelable