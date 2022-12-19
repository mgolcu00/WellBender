package com.mertgolcu.wellbender.ui.main

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.OnChangedCallback
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseFragment
import com.mertgolcu.wellbender.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {
    override fun getViewModelClass() = MainViewModel::class.java


    private var mainFragmentAdapter: MainFragmentAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    binding.viewPager.currentItem = HOME_POSITION
                    return@setOnItemSelectedListener true
                }
                R.id.menu_explore -> {
                    binding.viewPager.currentItem = EXPLORE_POSITION
                    return@setOnItemSelectedListener true
                }
                R.id.menu_fitness -> {
                    binding.viewPager.currentItem = FITNESS_POSITION
                    return@setOnItemSelectedListener true
                }
                R.id.menu_history -> {
                    binding.viewPager.currentItem = HISTORY_POSITION
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    private fun initAdapter() {
        mainFragmentAdapter = MainFragmentAdapter(
            requireActivity()
        )
        binding.viewPager.adapter = mainFragmentAdapter
        binding.viewPager.isUserInputEnabled = false
        // binding.viewPager.isSaveEnabled=false
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    HOME_POSITION -> {
                        binding.bottomNavigationView.menu.findItem(R.id.menu_home).isChecked =
                            true
                    }
                    EXPLORE_POSITION -> {
                        binding.bottomNavigationView.menu.findItem(R.id.menu_explore).isChecked =
                            true
                    }
                    FITNESS_POSITION -> {
                        binding.bottomNavigationView.menu.findItem(R.id.menu_fitness).isChecked =
                            true
                    }
                    HISTORY_POSITION -> {
                        binding.bottomNavigationView.menu.findItem(R.id.menu_history).isChecked =
                            true
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.viewPager.adapter = null
    }

    companion object {
        const val HOME_POSITION = 0
        const val EXPLORE_POSITION = 1
        const val FITNESS_POSITION = 2
        const val HISTORY_POSITION = 3

    }
}