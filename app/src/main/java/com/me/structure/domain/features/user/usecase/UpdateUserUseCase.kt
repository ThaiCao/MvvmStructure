package com.me.structure.domain.features.user.usecase

import com.me.structure.domain.features.user.model.User
import com.me.structure.domain.features.user.repository.UserRepository
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody

class UpdateUserUseCase(private val repository: UserRepository) {
    operator fun invoke(user: User): Single<ResponseBody>{
        return repository.updateUser(user)
    }
}