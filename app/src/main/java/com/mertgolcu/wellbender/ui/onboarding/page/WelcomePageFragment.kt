package com.mertgolcu.wellbender.ui.onboarding.page

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseFragment
import com.mertgolcu.wellbender.core.base.BaseViewModel
import com.mertgolcu.wellbender.databinding.FragmentPageWelcomeBinding
import com.mertgolcu.wellbender.ui.onboarding.OnBoardingFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Mert Gölcü on 19.12.2022.
 */

@AndroidEntryPoint
class WelcomePageFragment :
    BaseFragment<FragmentPageWelcomeBinding, WelcomePageViewModel>(R.layout.fragment_page_welcome) {
    override fun getViewModelClass() = WelcomePageViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        binding.buttonContinue.setOnClickListener {
            val navHostFragment =
                requireActivity().supportFragmentManager.fragments[0] as NavHostFragment
            val parent =
                navHostFragment.childFragmentManager.primaryNavigationFragment as OnBoardingFragment
            parent.startApp()
        }
    }

    companion object {
        fun newInstance(): WelcomePageFragment {
            val fragment = WelcomePageFragment()
            return fragment
        }
    }
}

@HiltViewModel
class WelcomePageViewModel @Inject constructor() : BaseViewModel() {

}