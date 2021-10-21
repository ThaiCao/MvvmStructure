package com.me.structure.domain.features.user.repository

import com.me.structure.domain.features.auth.model.LoginData
import com.me.structure.domain.features.user.model.User
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody

interface UserRepository {
    fun updateUser(user: User): Single<ResponseBody>

    fun logOut(): Single<ResponseBody>

    fun login(loginData: LoginData): Single<ResponseBody>
}