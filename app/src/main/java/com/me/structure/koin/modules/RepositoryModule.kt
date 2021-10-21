package com.me.structure.koin.modules

import com.me.structure.data.repository.features.auth.AuthRepositoryImpl
import com.me.structure.data.repository.features.user.MovieRepositoryImpl
import com.me.structure.data.repository.features.user.UserRepositoryImpl
import com.me.structure.domain.features.auth.repository.AuthRepository
import com.me.structure.domain.features.movie.repository.MovieRepository
import com.me.structure.domain.features.user.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory { AuthRepositoryImpl(get()) as AuthRepository }
    factory { UserRepositoryImpl(get()) as UserRepository }
    factory { MovieRepositoryImpl(get()) as MovieRepository }
}