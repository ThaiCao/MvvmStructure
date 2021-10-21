package com.me.structure.domain.features.user.usecase

import com.me.structure.domain.features.user.repository.UserRepository
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody

class LogoutUseCase(private val repository: UserRepository) {
    operator fun invoke(): Single<ResponseBody> {
        return repository.logOut()
    }
}