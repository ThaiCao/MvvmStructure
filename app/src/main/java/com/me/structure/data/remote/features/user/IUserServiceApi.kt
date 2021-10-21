package com.me.structure.data.remote.features.user

import com.me.structure.data.remote.ApiEndPoint
import com.me.structure.domain.features.auth.model.LoginData
import com.me.structure.domain.features.user.model.User
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface IUserServiceApi {
    @POST(ApiEndPoint.UPDATE_USER)
    fun updateUser(@Header("Authorization") token: String, user: User): Single<ResponseBody>

    @POST(ApiEndPoint.LOG_OUT_USER)
    fun logOut(): Single<ResponseBody>

    @POST(ApiEndPoint.LOG_IN_USER)
    fun logIn(@Body loginData: LoginData): Single<ResponseBody>

}