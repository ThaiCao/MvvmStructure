package com.me.structure.koin.components

import com.me.structure.koin.modules.*

val applicationComponent = listOf(
    createDomainModule(), createRemoteModule(), repositoryModule,
    retrofitModule, viewModelModule, localModule
)