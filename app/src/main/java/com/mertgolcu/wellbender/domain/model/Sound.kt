package com.mertgolcu.wellbender.domain.model

import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.ext.formatTimeToMeaning
import com.mertgolcu.wellbender.ext.getId

/**
 * Created by Mert Gölcü on 26.12.2022.
 */

data class Sound(
    val id: String,
    val title: String,
    val imageUrl: String,
    val duration: Long,// as millis
    val soundUrl: String,
    val tags: List<SoundTag>? = null
) {
    val formattedDuration: String
        get() {
            return duration.formatTimeToMeaning()
        }
}

data class SoundTag(
    val id: String,
    val name: String,
    val color: String? = null
) {
    val colorId: Int?
        get() {
            return if (color != null)
                R.color::class.java.getId(color)
            else
                null
        }
}