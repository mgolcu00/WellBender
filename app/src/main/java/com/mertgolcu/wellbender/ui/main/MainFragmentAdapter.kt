package com.mertgolcu.wellbender.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mertgolcu.wellbender.ui.explore.ExploreFragment
import com.mertgolcu.wellbender.ui.explore.ExploreViewModel
import com.mertgolcu.wellbender.ui.fitness.FitnessFragment
import com.mertgolcu.wellbender.ui.history.HistoryFragment
import com.mertgolcu.wellbender.ui.home.HomeFragment
import com.mertgolcu.wellbender.ui.sounds.SoundsFragment

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

class MainFragmentAdapter(
    private val fragmentActivity: FragmentActivity
) :
    FragmentStateAdapter(fragmentActivity) {

    private val homeFragment = HomeFragment.newInstance()
    private val exploreFragment = ExploreFragment.newInstance()
    private val soundsFragment = SoundsFragment()
    private val fragmentList = arrayListOf(
        homeFragment,
        exploreFragment,
        soundsFragment,// fitnessFragment
        HistoryFragment()
    )

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]

}