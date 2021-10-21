package com.me.structure.koin.modules

import com.me.structure.data.remote.features.auth.AuthDataSource
import com.me.structure.data.remote.features.movie.MovieDataSource
import com.me.structure.data.remote.features.user.UserDataDataSource
import org.koin.dsl.module

fun createRemoteModule() = module {
    factory { AuthDataSource(get()) }
    factory { UserDataDataSource(get()) }
    factory { MovieDataSource(get()) }

}