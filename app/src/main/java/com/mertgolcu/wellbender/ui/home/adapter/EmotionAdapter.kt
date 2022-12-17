package com.mertgolcu.wellbender.ui.home.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.databinding.ItemEmotionBinding
import com.mertgolcu.wellbender.domain.model.Emotion
import com.mertgolcu.wellbender.ext.loadImage
import com.mertgolcu.wellbender.ext.loadImageFromLocal
import java.net.URI
import java.net.URL

/**
 * Created by Mert Gölcü on 16.12.2022.
 */

class EmotionAdapter : ListAdapter<Emotion, EmotionAdapter.EmotionViewHolder>(
    object : DiffUtil.ItemCallback<Emotion>() {
        override fun areItemsTheSame(oldItem: Emotion, newItem: Emotion) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Emotion, newItem: Emotion) =
            oldItem.id == newItem.id

    }
) {

    var onClickItem: ((item: Emotion) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmotionViewHolder {
        val binding = ItemEmotionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EmotionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmotionViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }


    inner class EmotionViewHolder(private val binding: ItemEmotionBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Emotion) {
            // icon logic
            item.iconUrl?.let {
                if (URLUtil.isValidUrl(it)) {
                    loadImage(it)
                } else {
                    loadImageFromLocal(item.iconId)
                }
            }
            // icon tint logic
            item.iconTint?.let {
                if (it[0] == '#') {
                    setIconTintFromHex(item.iconTint)
                } else {
                    setIconTintFromLocal(item.iconTintId)
                }
            }
            // color logic
            item.color?.let {
                if (it[0] == '#') {
                    loadColorWithHex(item.color)
                } else {
                    loadColor(item.colorId)
                }
            }
            item.title.let {
                binding.textViewEmotionTitle.text = it
            }
            binding.root.setOnClickListener {
                onClickItem?.invoke(item)
            }
        }


        private fun loadImage(url: String) {
            binding.imageViewEmotion.loadImage(url, true)
        }

        private fun loadImageFromLocal(id: Int) {
            binding.imageViewEmotion.loadImageFromLocal(id, true)
        }

        private fun loadColor(@ColorRes colorId: Int) {
            binding.layoutCard.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    binding.root.context,
                    colorId
                )
            )
        }

        private fun loadColorWithHex(color: String) {
            binding.layoutCard.backgroundTintList = ColorStateList.valueOf(
                Color.parseColor(color)
            )
        }

        private fun setIconTintFromHex(color: String) {
            binding.imageViewEmotion.imageTintList = ColorStateList.valueOf(
                Color.parseColor(color)
            )
        }

        private fun setIconTintFromLocal(id: Int) {
            binding.imageViewEmotion.imageTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    binding.root.context,
                    id
                )
            )
        }
    }

}