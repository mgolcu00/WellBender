package com.mertgolcu.wellbender.domain.model.card

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.ext.getId

/**
 * Created by Mert Gölcü on 18.12.2022.
 *
 * @suppress THIS CLASS CANCELLED
 */

data class BaseCardModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String? = null,
    val backgroundTintColor: String = DEFAULT_BACKGROUND_TINT,
    val button: CardButton = DEFAULT_BUTTON,
    val textColor: String = DEFAULT_TEXT_COLOR,
) {
    val backgroundTintColorId: Int
        get() {
            return R.color::class.java.getId(backgroundTintColor)
        }
    val textColorId: Int
        get() {
            return R.color::class.java.getId(textColor)
        }

    val imageId: Int
        get() {
            return (imageUrl?.let { R.drawable::class.java.getId(it) }
                ?: DEFAULT_IMAGE_ID)
        }
    var imageTintColorId: Int? = null
        get() {
            if (imageUrl != null) return null
            return field ?: DEFAULT_IMAGE_TINT_COLOR
        }


    @ColorInt
    fun getBackgroundTintColorRaw(context: Context? = null): Int {
        if (backgroundTintColor[0] == '#') {
            return Color.parseColor(backgroundTintColor)
        } else {
            context?.let {
                return ContextCompat.getColor(it, backgroundTintColorId)
            }
        }
        return Color.RED
    }

    // get Text Color Raw
    @ColorInt
    fun getTextColorRaw(context: Context? = null): Int {
        if (textColor[0] == '#') {
            return Color.parseColor(textColor)
        } else {
            context?.let {
                return ContextCompat.getColor(it, textColorId)
            }
        }
        return Color.RED
    }

    companion object {
        const val DEFAULT_BACKGROUND_TINT = "night_green"
        val DEFAULT_BUTTON = CardButton(
            text = "Continue"
        )
        const val DEFAULT_TEXT_COLOR = "white"
        const val DEFAULT_IMAGE_ID = R.drawable.ic_health
        const val DEFAULT_IMAGE_TINT_COLOR = R.color.soft_green
    }
}

data class CardButton(
    val text: String,
    val backgroundTintColor: String = DEFAULT_BACKGROUND_TINT,
    val icon: String = DEFAULT_ICON,
    val textColor: String = DEFAULT_TEXT_COLOR,
    val iconColor: String = DEFAULT_ICON_COLOR
) {
    val backgroundTintColorId: Int
        get() {
            return R.color::class.java.getId(backgroundTintColor)
        }
    val iconId: Int
        get() {
            return R.drawable::class.java.getId(icon)
        }
    val textColorId: Int
        get() {
            return R.color::class.java.getId(textColor)
        }
    val iconColorId: Int
        get() {
            return R.color::class.java.getId(iconColor)
        }


    // get background tint  color raw value
    @ColorInt
    fun getBackgroundTintColorRaw(context: Context? = null): Int {
        if (backgroundTintColor[0] == '#') {
            return Color.parseColor(backgroundTintColor)
        } else {
            context?.let {
                return ContextCompat.getColor(it, backgroundTintColorId)
            }
        }
        return Color.RED
    }

    // get Text Color Raw
    @ColorInt
    fun getTextColorRaw(context: Context? = null): Int {
        if (textColor[0] == '#') {
            return Color.parseColor(textColor)
        } else {
            context?.let {
                return ContextCompat.getColor(it, textColorId)
            }
        }
        return Color.RED
    }

    // get Icon Color Raw
    @ColorInt
    fun getIconColorRaw(context: Context? = null): Int {
        if (iconColor[0] == '#') {
            return Color.parseColor(iconColor)
        } else {
            context?.let {
                return ContextCompat.getColor(it, iconColorId)
            }
        }
        return Color.RED
    }

    companion object {
        const val DEFAULT_BACKGROUND_TINT = "deep"
        const val DEFAULT_ICON = "ic_chevron_right"
        const val DEFAULT_TEXT_COLOR = "white"
        const val DEFAULT_ICON_COLOR = "white"
    }
}


enum class CardType {
    WARNING,
    POPULAR,
    NORMAL,
    SOFT,
}

data class MockTodayCard(
    val title: String,
    val description: String,
    val buttonText: String,
    val imageUrl: String? = null,
    val imageId: Int = R.drawable.ic_meetup
)