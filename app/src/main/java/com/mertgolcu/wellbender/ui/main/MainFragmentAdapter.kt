package com.mertgolcu.wellbender.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mertgolcu.wellbender.ui.explore.ExploreFragment
import com.mertgolcu.wellbender.ui.explore.ExploreViewModel
import com.mertgolcu.wellbender.ui.fitness.FitnessFragment
import com.mertgolcu.wellbender.ui.history.HistoryFragment
import com.mertgolcu.wellbender.ui.home.HomeFragment

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

class MainFragmentAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = arrayListOf(
        HomeFragment(),
        ExploreFragment(),
        FitnessFragment(),
        HistoryFragment()
    )

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]
}