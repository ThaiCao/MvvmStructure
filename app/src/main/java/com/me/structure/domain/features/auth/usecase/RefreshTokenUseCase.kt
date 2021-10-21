package com.me.structure.domain.features.auth.usecase

import com.me.structure.data.remote.model.TokenRequest
import com.me.structure.domain.features.auth.repository.AuthRepository
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody

class RefreshTokenUseCase(private val repository: AuthRepository) {
    operator fun invoke(tokenRequest: TokenRequest): Single<ResponseBody> {
        return repository.refreshToken(tokenRequest)
    }
}