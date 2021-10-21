package com.me.structure.utils.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.me.structure.R

object ActivityUtils {
    fun addFragmentToActivity(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        frameId: Int
    ) {
        if (fragmentManager == null || fragment == null) return
        val transaction =
            fragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.slide_from_right, R.anim.slide_to_left,
            R.anim.slide_from_left, R.anim.slide_to_right
        ) //default
        transaction.add(frameId, fragment)
            .addToBackStack(null)
        transaction.commit()
    }

    fun addFragmentToActivityWithTag(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        frameId: Int, tag: String
    ) {
        if (fragmentManager == null || fragment == null) return
        val transaction =
            fragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.slide_from_right, R.anim.slide_to_left,
            R.anim.slide_from_left, R.anim.slide_to_right
        ) //default
        transaction.add(frameId, fragment)
        transaction.commit()
    }

    fun replaceFragmentInActivity(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        frameId: Int
    ) {
        if (fragmentManager == null || fragment == null) return
        val transaction =
            fragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.slide_from_right, R.anim.slide_to_left,
            R.anim.slide_from_left, R.anim.slide_to_right
        ) //default
        transaction.replace(frameId, fragment)
        transaction.commit()
    }

    fun replaceFragmentInActivity(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        frameId: Int,
        tag: String?
    ) {
        if (fragmentManager == null || fragment == null) return
        val transaction =
            fragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.slide_from_right, R.anim.slide_to_left,
            R.anim.slide_from_left, R.anim.slide_to_right
        ) //default
        transaction.replace(frameId, fragment, tag)
        transaction.commit()
    }

    fun popFragment(fragmentManager: FragmentManager) {
        fragmentManager.popBackStack()
    }
}