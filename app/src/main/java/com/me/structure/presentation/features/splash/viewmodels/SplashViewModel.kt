package com.me.structure.presentation.features.splash.viewmodels

import androidx.lifecycle.MutableLiveData
import com.me.structure.bases.BaseViewModel
import com.me.structure.domain.features.user.usecase.UserUseCaseManager
import com.me.structure.sharedPrefs

class SplashViewModel(private val userUseCaseManager: UserUseCaseManager) : BaseViewModel(){
    val goToLoginScreen = MutableLiveData<Boolean>()
    val goToMainScreen = MutableLiveData<Boolean>()

    fun checkLoginInformation(){
        if(sharedPrefs.getUserInfo()!=null){
            goToMainScreen.value = true
        }else{
            goToLoginScreen.value = true
        }
    }
}