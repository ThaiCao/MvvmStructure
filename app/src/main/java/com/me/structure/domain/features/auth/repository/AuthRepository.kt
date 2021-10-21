package com.me.structure.domain.features.auth.repository

import com.me.structure.data.remote.model.TokenRequest
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody

interface AuthRepository {
    fun exchangeToken(tokenRequest: TokenRequest): Single<ResponseBody>
    fun refreshToken(tokenRequest: TokenRequest): Single<ResponseBody>
}