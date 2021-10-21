package com.me.structure.data.repository.features.auth

import com.me.structure.data.remote.features.auth.AuthDataSource
import com.me.structure.data.remote.model.TokenRequest
import com.me.structure.domain.features.auth.repository.AuthRepository
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override fun exchangeToken(tokenRequest: TokenRequest): Single<ResponseBody> {
        return authDataSource.exchangeToken(tokenRequest)
    }

    override fun refreshToken(tokenRequest: TokenRequest): Single<ResponseBody> {
        return authDataSource.refreshToken(tokenRequest)
    }
}