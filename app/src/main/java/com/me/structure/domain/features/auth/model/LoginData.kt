package com.me.structure.domain.features.auth.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class LoginData(
    val email: String? = "",
    val password: String? = ""
) : Parcelable