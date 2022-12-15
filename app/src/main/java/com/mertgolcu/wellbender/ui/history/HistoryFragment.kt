package com.mertgolcu.wellbender.ui.history

import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseFragment
import com.mertgolcu.wellbender.databinding.FragmentHistoryBinding
import com.mertgolcu.wellbender.ui.history.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

@AndroidEntryPoint
class HistoryFragment :
    BaseFragment<FragmentHistoryBinding, HistoryViewModel>(R.layout.fragment_history) {
    override fun getViewModelClass() = HistoryViewModel::class.java
}