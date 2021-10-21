package com.me.structure.koin.modules

import com.me.structure.domain.features.auth.usecase.ExchangeTokenUseCase
import com.me.structure.domain.features.auth.usecase.RefreshTokenUseCase
import com.me.structure.domain.features.movie.usecase.GetListMovieUseCase
import com.me.structure.domain.features.movie.usecase.GetMovieDetailUseCase
import com.me.structure.domain.features.movie.usecase.MovieUseCaseManager
import com.me.structure.domain.features.user.usecase.LoginUseCase
import com.me.structure.domain.features.user.usecase.LogoutUseCase
import com.me.structure.domain.features.user.usecase.UpdateUserUseCase
import com.me.structure.domain.features.user.usecase.UserUseCaseManager
import org.koin.dsl.module

fun createDomainModule() = module {

    factory { ExchangeTokenUseCase(get()) }
    factory { RefreshTokenUseCase(get()) }

    factory { LogoutUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { UpdateUserUseCase(get()) }
    factory { UserUseCaseManager(get(), get(), get()) }

    factory { GetListMovieUseCase(get()) }
    factory { GetMovieDetailUseCase(get()) }
    factory { MovieUseCaseManager(get(),get()) }
}
