package com.mertgolcu.wellbender.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseFragment
import com.mertgolcu.wellbender.databinding.FragmentHomeBinding
import com.mertgolcu.wellbender.ui.home.adapter.EmotionAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override fun getViewModelClass() = HomeViewModel::class.java

    private var emotionAdapter: EmotionAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        initWelcome()
        initEmotion()
        initTodayCard()
    }


    private fun initObservers() {
        viewModel.emojiList.observe(viewLifecycleOwner) {
            emotionAdapter?.submitList(it)
        }
    }

    private fun initWelcome() {
        loadAvatar()
    }

    private fun initEmotion() {
        initEmotionAdapter()
    }


    private fun initTodayCard() {
        binding.buttonTodayCard.setOnClickListener {
            // todo : on click today card button
        }
    }

    private fun initEmotionAdapter() {
        emotionAdapter = EmotionAdapter()
        binding.recyclerViewEmotions.layoutManager =
            LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        binding.recyclerViewEmotions.adapter = emotionAdapter
        emotionAdapter?.onClickItem = {
            // todo : On Click Emotion
        }
    }

    private fun loadAvatar() {
        Glide.with(requireActivity())
            .load(viewModel.avatarUrl.value)
            .placeholder(R.drawable.ic_person)
            .circleCrop()
            .into(binding.imageViewAvatar)
    }
}