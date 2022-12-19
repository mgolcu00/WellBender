package com.mertgolcu.wellbender.domain.model


import android.text.format.DateUtils
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.ext.formatDate
import com.mertgolcu.wellbender.ext.getId

/**
 * Created by Mert Gölcü on 16.12.2022.
 */

data class Emotion(
    val id: String,
    val title: String,
    val iconUrl: String? = null,
    val iconTint: String? = null,
    val color: String? = null,
) {


    val iconId: Int
        get() {
            return R.drawable::class.java.getId(iconUrl ?: "default")
        }
    val colorId: Int
        get() {
            return R.color::class.java.getId(color ?: "default")
        }
    val iconTintId: Int
        get() {
            return R.color::class.java.getId(iconTint ?: "default")
        }

    companion object {
        const val DEFAULT_ICON_NAME = "default"
        const val DEFAULT_COLOR_NAME = "default"
    }
}

data class EmotionMood(
    val id: String,
    val emotion: Emotion,
    val date: Long
) {
    val prettyDate: String
        get() {
            return date.formatDate()
        }
    val isToday: Boolean
        get() {
            return DateUtils.isToday(date)
        }
}