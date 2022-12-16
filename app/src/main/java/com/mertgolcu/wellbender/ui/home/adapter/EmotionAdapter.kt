package com.mertgolcu.wellbender.ui.home.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.databinding.ItemEmotionBinding
import com.mertgolcu.wellbender.ui.home.MockEmotion

/**
 * Created by Mert Gölcü on 16.12.2022.
 */

class EmotionAdapter : ListAdapter<MockEmotion, EmotionAdapter.EmotionViewHolder>(
    object : DiffUtil.ItemCallback<MockEmotion>() {
        override fun areItemsTheSame(oldItem: MockEmotion, newItem: MockEmotion) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: MockEmotion, newItem: MockEmotion) =
            oldItem.text == newItem.text

    }
) {

    var onClickItem: ((item: MockEmotion) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmotionViewHolder {
        val binding = ItemEmotionBinding.inflate(LayoutInflater.from(parent.context))
        return EmotionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmotionViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }


    inner class EmotionViewHolder(private val binding: ItemEmotionBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: MockEmotion) {
            if (item.imageUrl.isNullOrEmpty()) {
                loadImageFromLocal(item.image)
            } else {
                loadImage(item.imageUrl)
            }
            item.color?.let {
                loadColor(it)
            }
            item.text.let {
                binding.textViewEmotionTitle.text = it
            }
            binding.root.setOnClickListener {
                onClickItem?.invoke(item)
            }
        }

        private fun loadImage(url: String) {
            Glide.with(binding.imageViewEmotion)
                .load(url)
                .placeholder(R.drawable.ic_person)
                .circleCrop()
                .into(binding.imageViewEmotion)
        }

        private fun loadImageFromLocal(id: Int) {
            Glide.with(binding.imageViewEmotion)
                .load(id)
                .placeholder(R.drawable.ic_person)
                .circleCrop()
                .into(binding.imageViewEmotion)
        }

        private fun loadColor(@ColorRes colorId: Int) {
            binding.layoutCard.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    binding.root.context,
                    colorId
                )
            )
        }

    }

}