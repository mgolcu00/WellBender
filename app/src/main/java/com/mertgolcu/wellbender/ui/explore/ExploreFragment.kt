package com.mertgolcu.wellbender.ui.explore

import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseFragment
import com.mertgolcu.wellbender.databinding.FragmentExploreBinding
import com.mertgolcu.wellbender.databinding.FragmentHistoryBinding
import com.mertgolcu.wellbender.ui.history.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

@AndroidEntryPoint
class ExploreFragment :
    BaseFragment<FragmentExploreBinding, ExploreViewModel>(R.layout.fragment_explore) {
    override fun getViewModelClass() = ExploreViewModel::class.java
}