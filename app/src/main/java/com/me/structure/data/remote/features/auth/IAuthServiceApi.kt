package com.me.structure.data.remote.features.auth

import com.me.structure.data.remote.ApiEndPoint
import com.me.structure.data.remote.model.TokenRequest
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface IAuthServiceApi {
    @POST(ApiEndPoint.EXCHANGE_TOKEN)
    fun exchangeToken(@Body tokenRequest: TokenRequest): Single<ResponseBody>

    @POST(ApiEndPoint.REFRESH_TOKEN)
    fun refreshToken(@Body tokenRequest: TokenRequest): Single<ResponseBody>
}