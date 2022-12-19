package com.mertgolcu.wellbender.ui.onboarding.page

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by Mert Gölcü on 19.12.2022.
 */

class OnBoardingPageAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val fragments = arrayListOf(
        InputNameFragment.newInstance(),
        WelcomePageFragment.newInstance()
    )

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}