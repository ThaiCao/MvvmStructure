package com.me.structure.presentation.features.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import com.me.structure.R
import com.me.structure.bases.BaseFragment
import com.me.structure.bases.addTo
import com.me.structure.databinding.FragmentLoginBinding
import com.me.structure.presentation.features.login.viewmodels.LoginViewModel
import com.me.structure.utils.AppConstConfig.Companion.DELAY_TIME
import com.me.structure.utils.common.NavigationUtils
import com.me.structure.utils.common.TextContentUtils
import com.me.structure.utils.common.ValidateUtils
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class LoginFragment : BaseFragment(){

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModel()
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        _binding?.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleCallBack()
        handleClick()
        loginViewModel.isEnableLoginButton.value = true
    }

    private fun handleCallBack() {
        loginViewModel.loginSuccess.observe(viewLifecycleOwner) {
            if (it)
                NavigationUtils.navigateToMainActivity(context)
        }
        loginViewModel.errorText.observe(viewLifecycleOwner) {
            binding.tilPassword.error = it

        }
        loginViewModel.isEnableLoginButton.observe(viewLifecycleOwner) {
            binding.loginButton.isEnabled = it
        }
        loginViewModel.isShowProgress.observe(viewLifecycleOwner) {
            binding.vProgress.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun handleClick() {
        binding.loginButton.clicks().throttleFirst(DELAY_TIME, TimeUnit.MILLISECONDS).subscribe {
            loginAccount()
        }.addTo(compositeDisposable)
        binding.registerButton.clicks().throttleFirst(DELAY_TIME, TimeUnit.MILLISECONDS).subscribe {
//            navigateToRegister(context)
        }.addTo(compositeDisposable)
        binding.tvForgotPass.clicks().throttleFirst(DELAY_TIME, TimeUnit.MILLISECONDS).subscribe {
//            navigateToResetPasswordActivity(context)
        }.addTo(compositeDisposable)

        binding.btnBack.clicks().throttleFirst(DELAY_TIME, TimeUnit.MILLISECONDS)
            .subscribe {
                activity?.finish()
            }.addTo(compositeDisposable)

        binding.tilEmail.editText?.textChanges()?.skip(1)?.subscribe {
            handleLogicValidateEmail()
        }?.addTo(compositeDisposable)

        binding.tilPassword.editText?.textChanges()?.skip(1)?.subscribe {
            if (binding.tilPassword.editText?.text?.length!! > 0)
                handleLogicValidatePassword()
        }?.addTo(compositeDisposable)
    }

    private fun handleLogicValidateEmail(): Boolean {
        val emailInput: String = binding.tilEmail.editText?.text.toString().trim()
        binding.tilEmail.error =
            context?.let { TextContentUtils.getTextValidateEmail(emailInput, it) }
        return ValidateUtils.isValidateEmail(emailInput)
    }

    private fun handleLogicValidatePassword(): Boolean {
        val passwordInput: String = binding.tilPassword.editText?.text.toString().trim()
        binding.tilPassword.error = context?.let {
            TextContentUtils.getTextValidatePassword(
                passwordInput,
                it
            )
        }
        return ValidateUtils.isValidatePassword(passwordInput)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        compositeDisposable.clear()
    }

    private fun loginAccount() {
//        if (!handleLogicValidateEmail() or !handleLogicValidatePassword()) {
//            return
//        }
        loginViewModel.isEnableLoginButton.value = false
        loginRequest()
    }

    private fun loginRequest() {
        activity?.let {
            loginViewModel.logInUser(
                binding.usernameEditText.text.toString(),
                binding.passwordEditText.text.toString(), it
            )
        }
    }

    companion object {
        fun newInstance(args: Bundle?) = LoginFragment().apply {
            arguments = args
        }
    }
}