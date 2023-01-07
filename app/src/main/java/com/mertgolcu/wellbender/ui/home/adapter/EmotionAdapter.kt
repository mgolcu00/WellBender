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
        val binding = ItemEmotionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
            EmotionHelper(item, binding).bind(onClickItem)
        }
    }

}