package com.mertgolcu.wellbender.ui.home.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.webkit.URLUtil
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.mertgolcu.wellbender.databinding.ItemEmotionBinding
import com.mertgolcu.wellbender.domain.model.Emotion
import com.mertgolcu.wellbender.ext.loadImage

/**
 * Created by Mert Gölcü on 7.01.2023.
 */

class EmotionHelper(private val emotion: Emotion, val binding: ItemEmotionBinding) {


    fun bind(onClickEmotion: ((emotion: Emotion) -> Unit)? = null) {
        // icon logic
        emotion.iconUrl?.let {
            if (URLUtil.isValidUrl(it)) {
                loadImage(it)
            } else {
                loadImageFromLocal(emotion.iconId)
            }
        }
        // icon tint logic
        emotion.iconTint?.let {
            if (it[0] == '#') {
                setIconTintFromHex(emotion.iconTint)
            } else {
                setIconTintFromLocal(emotion.iconTintId)
            }
        }
        // color logic
        emotion.color?.let {
            if (it[0] == '#') {
                loadColorWithHex(emotion.color)
            } else {
                loadColor(emotion.colorId)
            }
        }
        emotion.title.let {
            binding.textViewEmotionTitle.text = it
        }
        binding.root.setOnClickListener {
            onClickEmotion?.invoke(emotion)
        }
    }


    private fun loadImage(url: String) {
        binding.imageViewEmotion.loadImage(url, true)
    }

    private fun loadImageFromLocal(id: Int) {
        //  binding.imageViewEmotion.loadImageFromLocal(id, true)
        binding.imageViewEmotion.setImageResource(id)
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