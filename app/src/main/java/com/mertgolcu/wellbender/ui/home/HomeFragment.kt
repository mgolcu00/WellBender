package com.mertgolcu.wellbender.ui.home

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseFragment
import com.mertgolcu.wellbender.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override fun getViewModelClass() = HomeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadAvatar()
    }

    private fun loadAvatar() {
        Glide.with(requireActivity())
            .load(viewModel.avatarUrl.value)
            .placeholder(R.drawable.ic_person)
            .circleCrop()
            .into(binding.imageViewAvatar)
    }
}