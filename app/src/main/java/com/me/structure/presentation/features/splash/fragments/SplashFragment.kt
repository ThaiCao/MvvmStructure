package com.me.structure.presentation.features.splash.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.me.structure.R
import com.me.structure.bases.BaseFragment
import com.me.structure.databinding.FragmentSplashBinding
import com.me.structure.presentation.features.splash.viewmodels.SplashViewModel
import com.me.structure.utils.common.NavigationUtils
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment(){

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val splashViewModel: SplashViewModel by viewModel()
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        _binding?.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleCallBack()
        handleClick()
        splashViewModel.checkLoginInformation()
    }

    private fun handleCallBack() {
        splashViewModel.goToMainScreen.observe(viewLifecycleOwner) {
            Handler().postDelayed({
                if (it)
                    NavigationUtils.navigateToMainActivity(context)
            }, 3000)
        }
        splashViewModel.goToLoginScreen.observe(viewLifecycleOwner) {
            Handler().postDelayed({
                if (it)
                    NavigationUtils.navigateToLoginActivity(context)
            }, 3000)
        }
    }

    private fun handleClick() {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        compositeDisposable.clear()
    }

    companion object {
        fun newInstance(args: Bundle?) = SplashFragment().apply {
            arguments = args
        }
    }
}