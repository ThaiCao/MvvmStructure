package com.me.structure.data.remote.features.user

import com.me.structure.domain.features.auth.model.LoginData
import com.me.structure.domain.features.user.model.User
import com.me.structure.sharedPrefs

class UserDataDataSource(private val userServiceApi: IUserServiceApi) {

    fun updateUser(user: User) = userServiceApi.updateUser("Bearer ${sharedPrefs.loadFirebaseToken()}",user)

    fun logOut() = userServiceApi.logOut()

    fun login(loginData: LoginData) = userServiceApi.logIn(loginData)
}