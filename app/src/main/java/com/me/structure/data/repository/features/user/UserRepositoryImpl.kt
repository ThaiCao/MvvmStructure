package com.me.structure.data.repository.features.user

import com.me.structure.data.remote.features.user.UserDataDataSource
import com.me.structure.domain.features.auth.model.LoginData
import com.me.structure.domain.features.user.model.User
import com.me.structure.domain.features.user.repository.UserRepository
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody

class UserRepositoryImpl (
    private val userDataDataSource: UserDataDataSource
) : UserRepository {
    override fun updateUser(user: User): Single<ResponseBody> {
        return userDataDataSource.updateUser(user)
    }

    override fun logOut(): Single<ResponseBody> {
        return userDataDataSource.logOut()
    }

    override fun login(loginData: LoginData): Single<ResponseBody> {
        return userDataDataSource.login(loginData)
    }
}