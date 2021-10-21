package com.me.structure.koin.modules

import com.me.structure.BuildConfig
import com.me.structure.data.local.sharedpreferences.PrefKeys
import com.me.structure.data.remote.features.auth.IAuthServiceApi
import com.me.structure.data.remote.features.movie.IMovieServiceApi
import com.me.structure.data.remote.features.user.IUserServiceApi
import com.me.structure.sharedPrefs
import com.me.structure.utils.AppConstConfig.Companion.SERVER_AUTHEN_URL
import com.me.structure.utils.AppConstConfig.Companion.SERVER_USER_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    factory { createOkHttpClient() }
    single { provideAPIService<IAuthServiceApi>(get(), SERVER_AUTHEN_URL) }
    single { provideAPIService<IUserServiceApi>(get(), SERVER_USER_URL) }
    single { provideAPIService<IMovieServiceApi>(get(), BuildConfig.MOVIE_BASE_URL) }
//    single { provideAPIService<IABCServiceApi>(get(), SERVER_ABC_URL) }
//    single { provideAPIService<IABCServiceApi>(get(), SERVER_ABC_URL) }
//    single { provideAPIService<IABCServiceApi>(get(), SERVER_ABC_URL) }
}

fun createOkHttpClient(isSnapMd: Boolean = false): OkHttpClient {
    val headersInterceptor: Interceptor = Interceptor { chain ->
        val token = sharedPrefs.getStringData(PrefKeys.TOKEN).orEmpty()
        val original: Request = chain.request()
        val request: Request = original.newBuilder()
            /*   .addHeader(
                   "authorization",
                   "Bearer ${sharedPrefs.getStringData(PrefKeys.TOKEN)}"
               )
               .addHeader("x-developer-id", "dev-key-aa-a-a-a--aa-a-a")
               .addHeader("x-api-key", "apikey-a-a-a-a-a-a-a")*/
            .method(original.method, original.body)
            .build()

        chain.proceed(request)
    }

    val httpClient = OkHttpClient.Builder()
    httpClient.connectTimeout(1, TimeUnit.MINUTES)
    httpClient.readTimeout(1, TimeUnit.MINUTES)
    httpClient.writeTimeout(1, TimeUnit.MINUTES)

    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    httpClient.interceptors().add(logging)

    httpClient.addNetworkInterceptor(headersInterceptor)
    return httpClient.build()
}

inline fun <reified T> provideAPIService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(okHttpClient).addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(T::class.java)
}


