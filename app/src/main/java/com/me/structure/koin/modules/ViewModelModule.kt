package com.me.structure.koin.modules

import com.me.structure.presentation.features.home.viewmodel.HomeViewModel
import com.me.structure.presentation.features.login.viewmodels.LoginViewModel
import com.me.structure.presentation.features.splash.viewmodels.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SplashViewModel(get())
    }

    viewModel {
        LoginViewModel(get())
    }

    viewModel {
        HomeViewModel(get())
    }

//    viewModel {
//        ValidateViewModel()
//    }
//
//    viewModel {
//        UserDetailViewModel(get())
//    }
//
//    viewModel {
//        NewPasswordViewModel()
//    }
//
//    viewModel {
//        ResetPasswordViewModel()
//    }
//    viewModel {
//        UserViewModel(get(),get())
//    }

}