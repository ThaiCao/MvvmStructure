package com.me.structure.presentation.features.main.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.me.structure.R
import com.me.structure.bases.BaseActivity
import com.me.structure.presentation.features.main.fragments.MainFragment
import com.me.structure.utils.common.ActivityUtils

class MainActivity : BaseActivity() {

    private var mainFragment : MainFragment? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        mainFragment = MainFragment.newInstance(intent.extras)
        ActivityUtils.addFragmentToActivityWithTag(
            supportFragmentManager,
            mainFragment!!, R.id.fragment_container, MainFragment.javaClass.name
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(from: Context): Intent = Intent(from, MainActivity::class.java).apply {
            putExtras(Bundle().apply {})
        }
    }
}