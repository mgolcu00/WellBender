package com.mertgolcu.wellbender.ui.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseFragment
import com.mertgolcu.wellbender.databinding.FragmentHomeBinding
import com.mertgolcu.wellbender.domain.model.card.BaseCardModel
import com.mertgolcu.wellbender.ui.home.adapter.CardAdapter
import com.mertgolcu.wellbender.ui.home.adapter.EmotionAdapter
import com.mertgolcu.wellbender.ui.home.adapter.EmotionHelper
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override fun getViewModelClass() = HomeViewModel::class.java

    private var emotionAdapter: EmotionAdapter? = null
    private var cardAdapter: CardAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initWelcome()
        initEmotion()
        initCards()
        initTodayCard()
        initBuyMoreCard()
        initQuote()
    }

    private fun initObservers() {
        viewModel.emojiList.observe(viewLifecycleOwner) {
            emotionAdapter?.submitList(it)
        }
        viewModel.todayEmotionMood.observe(viewLifecycleOwner) {
            binding.layoutDailyEmotion.isVisible = false
        }
        viewModel.cardList.observe(viewLifecycleOwner) {
            cardAdapter?.submitList(it)
        }
        viewModel.todayCard.observe(viewLifecycleOwner) {
            initTodayCard(null)
        }
        viewModel.buyMoreCard.observe(viewLifecycleOwner) {
            initBuyMoreCard(null)
        }
        viewModel.quote.observe(viewLifecycleOwner) {
            initQuote(it)
        }
        viewModel.avatarUrl.observe(viewLifecycleOwner) {
            Glide.with(requireActivity())
                .load(it)
                .placeholder(R.drawable.ic_cloud_download)
                .error(R.drawable.ic_health)
                .circleCrop()
                .into(binding.imageViewAvatar)
        }
        viewModel.todayEmotionMood.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.layoutCurrentEmotion.root.isVisible = true
                EmotionHelper(it.emotion, binding.layoutCurrentEmotion).bind {
                    binding.layoutCurrentEmotion.root.isVisible = false
                    binding.layoutDailyEmotion.isVisible = true
                }
            }
        }
    }

    private fun initWelcome() {

    }

    private fun initEmotion() {
        initEmotionAdapter()
    }

    private fun initCards() {
        cardAdapter = CardAdapter()
        binding.recyclerViewCards.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewCards.adapter = cardAdapter
    }

    private fun initTodayCard(todayCard: BaseCardModel? = null) {

        if (todayCard == null)
            binding.layoutTodayCard.isVisible = false
        todayCard?.let {
            binding.layoutTodayCard.isVisible = true
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
        if (buyMoreCard == null)
            binding.layoutBuyMore.isVisible = false
        buyMoreCard?.let {
            binding.layoutBuyMore.isVisible = true
            // background
            binding.layoutBuyMore.backgroundTintList = ColorStateList.valueOf(
                it.getBackgroundTintColorRaw(requireContext())
            )
            // title
            binding.textViewBuyMoreCardTitle.text = it.title
            binding.textViewBuyMoreCardTitle.setTextColor(
                it.getTextColorRaw(requireContext())
            )
            // description
            binding.textViewBuyMoreCardDescription.text = it.description
            binding.textViewBuyMoreCardDescription.setTextColor(
                it.getTextColorRaw(requireContext())
            )
            // button
            binding.buttonBuyMore.text = it.button.text
            binding.buttonBuyMore.backgroundTintList = ColorStateList.valueOf(
                it.button.getBackgroundTintColorRaw(requireContext())
            )
            binding.buttonBuyMore.setTextColor(
                it.button.getTextColorRaw(requireContext())
            )
            binding.buttonBuyMore.iconTint = ColorStateList.valueOf(
                it.button.getIconColorRaw(requireContext())
            )
            binding.buttonBuyMore.setIconResource(it.button.iconId)
            // image
            if (it.imageUrl != null && URLUtil.isValidUrl(it.imageUrl)) {
                Glide.with(requireActivity())
                    .load(it.imageUrl)
                    .placeholder(R.drawable.ic_cloud_download)
                    .into(binding.imageViewBuyMore)
                binding.imageViewBuyMore.imageTintList = null
            } else {
                binding.imageViewBuyMore.setImageResource(it.imageId)
                it.imageTintColorId?.let { imageTint ->
                    binding.imageViewBuyMore.imageTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(requireContext(), imageTint)
                    )
                }

            }
        }

        binding.buttonBuyMore.setOnClickListener {
            // todo : on click today card button
        }
    }

    private fun initQuote(quote: String? = "") {
        if (!quote.isNullOrEmpty()) {
            binding.textViewQuote.text = quote
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
            viewModel.setTodayEmotionMood(it)
        }
    }


    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }
    }
}