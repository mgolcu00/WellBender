package com.mertgolcu.wellbender.ui.sounds

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseFragment
import com.mertgolcu.wellbender.databinding.FragmentSoundsBinding
import com.mertgolcu.wellbender.ui.main.MainFragmentDirections
import com.mertgolcu.wellbender.ui.sounds.adapter.SoundAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mert Gölcü on 26.12.2022.
 */

@AndroidEntryPoint
class SoundsFragment :
    BaseFragment<FragmentSoundsBinding, SoundsViewModel>(R.layout.fragment_sounds) {
    override fun getViewModelClass() = SoundsViewModel::class.java

    private var soundAdapter: SoundAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initAdapter()

        viewModel.fetchSoundList()
    }

    private fun initObservers() {
        viewModel.soundList.observe(viewLifecycleOwner) {
            soundAdapter?.submitList(it)
        }
    }

    private fun initAdapter() {
        soundAdapter = SoundAdapter()
        soundAdapter?.onClickPlay = {
            viewModel.navigate(
                MainFragmentDirections
                    .actionMainFragmentToSoundListenFragment(it.id)
            )
        }
        binding.recyclerViewSounds.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewSounds.adapter = soundAdapter
    }
}