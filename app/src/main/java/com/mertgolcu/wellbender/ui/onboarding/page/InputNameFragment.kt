package com.mertgolcu.wellbender.ui.onboarding.page

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseFragment
import com.mertgolcu.wellbender.core.base.BaseViewModel
import com.mertgolcu.wellbender.databinding.FragmentPageInputNameBinding
import com.mertgolcu.wellbender.domain.store.WellBenderDataStoreManager
import com.mertgolcu.wellbender.ui.onboarding.OnBoardingFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mert Gölcü on 19.12.2022.
 */

@AndroidEntryPoint
class InputNameFragment :
    BaseFragment<FragmentPageInputNameBinding, InputNameViewModel>(R.layout.fragment_page_input_name) {
    override fun getViewModelClass() = InputNameViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initUI()
    }

    private fun initObservers() {
        viewModel.name.observe(viewLifecycleOwner) {
            binding.buttonContinue.isEnabled = !it.isNullOrBlank()
        }
    }

    private fun initUI() {
        binding.buttonContinue.setOnClickListener {
            val navHostFragment =
                requireActivity().supportFragmentManager.fragments[0] as NavHostFragment
            val parent =
                navHostFragment.childFragmentManager.primaryNavigationFragment as OnBoardingFragment
            viewModel.updateName()
            parent.nextPage()
        }
    }

    companion object {
        fun newInstance(): InputNameFragment {
            val fragment = InputNameFragment()
            return fragment
        }
    }
}

@HiltViewModel
class InputNameViewModel @Inject constructor(
    private val dataStoreManager: WellBenderDataStoreManager
) : BaseViewModel() {

    val name = MutableLiveData("")

    fun updateName() {
        viewModelScope.launch {
            name.value?.let {
                dataStoreManager.updateUserName(it)
            }
        }
    }
}