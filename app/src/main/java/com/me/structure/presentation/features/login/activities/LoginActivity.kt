package com.me.structure.presentation.features.login.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.me.structure.R
import com.me.structure.bases.BaseActivity
import com.me.structure.presentation.features.login.fragments.LoginFragment
import com.me.structure.utils.common.ActivityUtils

class LoginActivity : BaseActivity(){

    private var loginFragment: LoginFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        gotoLogin()
    }

    private fun gotoLogin() {
        loginFragment = LoginFragment.newInstance(Bundle())
        ActivityUtils.addFragmentToActivityWithTag(
            supportFragmentManager,
            loginFragment!!, R.id.fragment_container, LoginFragment.javaClass.name
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(from: Context): Intent = Intent(from, LoginActivity::class.java).apply {
            putExtras(Bundle().apply {})
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}