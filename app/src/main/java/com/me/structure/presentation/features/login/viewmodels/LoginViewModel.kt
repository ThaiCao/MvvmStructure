package com.me.structure.presentation.features.login.viewmodels

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.me.structure.bases.BaseViewModel
import com.me.structure.bases.addTo
import com.me.structure.domain.features.auth.model.LoginData
import com.me.structure.domain.features.user.usecase.UserUseCaseManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(private val userUseCaseManager: UserUseCaseManager) : BaseViewModel(){

    val loginSuccess = MutableLiveData<Boolean>()
    val errorText = MutableLiveData<String>()
    val isShowProgress = MutableLiveData<Boolean>()
    val isEnableLoginButton = MutableLiveData<Boolean>()

    init {
        loginSuccess.value = false
        isShowProgress.value = false
    }

    fun logInUser(email: String, password: String, activity: Activity) {
        isShowProgress.value = true
        userUseCaseManager.loginUseCase.invoke(LoginData(email = email, password = password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterSuccess{
                isShowProgress.value = false
                isEnableLoginButton.value = true
            }
            .subscribe(
                {
                loginSuccess.value = true
                },
                {
                    viewModelScope.launch {
                        delay(2000)
//                        isShowProgress.value = false
//                        isEnableLoginButton.value = true
//                        errorText.value = "Login failed!"
                        loginSuccess.value = true
                    }
                }
            )
            .addTo(compositeDisposables)
    }
}