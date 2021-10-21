package com.me.structure.presentation.features.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.me.structure.presentation.features.favorite.fragments.FavoriteFragment
import com.me.structure.presentation.features.home.fragments.HomeFragment
import com.me.structure.presentation.features.more.fragments.MoreFragment
import com.me.structure.presentation.features.recent.fragment.RecentFragment

class MainAdapter(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> RecentFragment()
            2 -> FavoriteFragment()
            else -> MoreFragment()
        }
    }
}