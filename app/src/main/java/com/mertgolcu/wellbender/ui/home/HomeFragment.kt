package com.mertgolcu.wellbender.ui.home

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseFragment
import com.mertgolcu.wellbender.databinding.FragmentHomeBinding
import com.mertgolcu.wellbender.domain.model.card.BaseCardModel
import com.mertgolcu.wellbender.domain.model.card.TodayCard
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
        initBuyMoreCard()
    }


    private fun initObservers() {
        viewModel.emojiList.observe(viewLifecycleOwner) {
            emotionAdapter?.submitList(it)
        }
        viewModel.todayCard.observe(viewLifecycleOwner) {
            initTodayCard(it)
        }
        viewModel.buyMoreCard.observe(viewLifecycleOwner) {
            initBuyMoreCard(it)
        }
    }

    private fun initWelcome() {
        loadAvatar()
    }

    private fun initEmotion() {
        initEmotionAdapter()
    }


    private fun initTodayCard(todayCard: BaseCardModel? = null) {

        todayCard?.let {

            // background
            binding.layoutTodayCard.backgroundTintList = ColorStateList.valueOf(
                it.getBackgroundTintColorRaw(requireContext())
            )

            // texts
            binding.textViewTodayCardTitle.text = it.title
            binding.textViewTodayCardTitle.setTextColor(
                it.getTextColorRaw(requireContext())
            )

            binding.textViewTodayCardDescription.text = it.description
            binding.textViewTodayCardDescription.setTextColor(
                it.getTextColorRaw(requireContext())
            )

            // button
            binding.buttonTodayCard.text = it.button.text
            binding.buttonTodayCard.backgroundTintList = ColorStateList.valueOf(
                it.button.getBackgroundTintColorRaw(requireContext())
            )
            binding.buttonTodayCard.setTextColor(
                it.button.getTextColorRaw(requireContext())
            )
            binding.buttonTodayCard.iconTint = ColorStateList.valueOf(
                it.button.getIconColorRaw(requireContext())
            )
            binding.buttonTodayCard.setIconResource(it.button.iconId)


            // image
            if (it.imageUrl != null && URLUtil.isValidUrl(it.imageUrl)) {
                Glide.with(requireActivity())
                    .load(it.imageUrl)
                    .placeholder(R.drawable.ic_cloud_download)
                    .into(binding.imageViewTodayCard)
                binding.imageViewTodayCard.imageTintList = null
            } else {
                binding.imageViewTodayCard.setImageResource(it.imageId)
                it.imageTintColorId?.let { imageTint ->
                    binding.imageViewTodayCard.imageTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(requireContext(), imageTint)
                    )
                }

            }
        }

        binding.buttonTodayCard.setOnClickListener {
            // todo : on click today card button
        }
    }


    private fun initBuyMoreCard(buyMoreCard: BaseCardModel? = null) {
        buyMoreCard?.let {
            binding.textViewBuyMoreCardTitle.text = it.title
            binding.textViewBuyMoreCardDescription.text = it.description
            binding.buttonBuyMore.text = it.button.text
            if (it.imageUrl != null && URLUtil.isValidUrl(it.imageUrl)) {
                Glide.with(requireActivity())
                    .load(it.imageUrl)
                    .placeholder(R.drawable.ic_cloud_download)
                    .into(binding.imageViewBuyMore)
                binding.imageViewBuyMore.imageTintList = null
            } else {
                binding.imageViewBuyMore.setImageResource(it.imageId)
                binding.imageViewBuyMore.imageTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(requireContext(), R.color.soft_green)
                )
            }
        }

        binding.buttonBuyMore.setOnClickListener {
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