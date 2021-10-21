package com.me.structure.presentation.features.splash.activities

import android.os.Bundle
import com.me.structure.R
import com.me.structure.bases.BaseActivity
import com.me.structure.presentation.features.splash.fragments.SplashFragment
import com.me.structure.utils.common.ActivityUtils

class SplashActivity : BaseActivity() {

    private var splashFragment: SplashFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        splashFragment = SplashFragment.newInstance(intent?.extras)
        ActivityUtils.addFragmentToActivityWithTag(
            supportFragmentManager,
            splashFragment!!, R.id.fragment_container, SplashFragment.javaClass.name
        )

    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}