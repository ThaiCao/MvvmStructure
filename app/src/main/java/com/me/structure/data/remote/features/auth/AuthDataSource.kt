package com.me.structure.data.remote.features.auth

import com.me.structure.data.remote.model.TokenRequest

class AuthDataSource(private val authServiceApi: IAuthServiceApi) {

    fun exchangeToken(tokenRequest: TokenRequest) = authServiceApi.exchangeToken(tokenRequest)
    fun refreshToken(tokenRequest: TokenRequest) = authServiceApi.refreshToken(tokenRequest)
}