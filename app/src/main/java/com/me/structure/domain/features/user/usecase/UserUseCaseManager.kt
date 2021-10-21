package com.me.structure.domain.features.user.usecase

class UserUseCaseManager (
    val updateUserUseCase: UpdateUserUseCase,
    val loginUseCase: LoginUseCase,
    val logoutUseCase: LogoutUseCase,
)