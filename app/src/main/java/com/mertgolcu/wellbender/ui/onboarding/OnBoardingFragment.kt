package com.mertgolcu.wellbender.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseFragment
import com.mertgolcu.wellbender.databinding.FragmentOnBoradingBinding
import com.mertgolcu.wellbender.ui.onboarding.page.OnBoardingPageAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mert Gölcü on 19.12.2022.
 */

@AndroidEntryPoint
class OnBoardingFragment :
    BaseFragment<FragmentOnBoradingBinding, OnBoardingViewModel>(R.layout.fragment_on_borading) {
    override fun getViewModelClass() = OnBoardingViewModel::class.java

    private var onBoardingPageAdapter: OnBoardingPageAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initPager()

    }

    private fun initObservers() {
        viewModel.currentPage.observe(viewLifecycleOwner) {

            binding.viewPagerOnBoardingPages.currentItem = it - 1
        }
    }

    private fun initPager() {
        onBoardingPageAdapter = OnBoardingPageAdapter(requireActivity())

        binding.viewPagerOnBoardingPages.adapter = onBoardingPageAdapter
        binding.viewPagerOnBoardingPages.isUserInputEnabled = false
        binding.viewPagerOnBoardingPages.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.currentPage.postValue(position + 1)
            }
        })
    }


    fun nextPage() {
        viewModel.nextPage()
    }

    fun prevPage() {
        viewModel.prevPage()
    }

    fun startApp() {
        viewModel.startApp()
    }

}