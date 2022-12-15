package com.mertgolcu.wellbender.ui.fitness

import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseFragment
import com.mertgolcu.wellbender.databinding.FragmentFitnessBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

@AndroidEntryPoint
class FitnessFragment :
    BaseFragment<FragmentFitnessBinding, FitnessViewModel>(R.layout.fragment_fitness) {
    override fun getViewModelClass() = FitnessViewModel::class.java
}