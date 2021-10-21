package com.me.structure.domain.features.user.usecase

import com.me.structure.domain.features.auth.model.LoginData
import com.me.structure.domain.features.user.repository.UserRepository
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody

class LoginUseCase(private val repository: UserRepository) {
    operator fun invoke(loginData: LoginData): Single<ResponseBody> {
        return repository.login(loginData)
    }
}