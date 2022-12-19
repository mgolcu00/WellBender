package com.mertgolcu.wellbender.domain.model.card

import android.graphics.Bitmap
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.ext.getId

/**
 * Created by Mert Gölcü on 18.12.2022.
 */

/**
@suppress DEPCRATED
 */
data class TodayCard(
    val title: String,
    val description: String? = String.toString(),
    val buttonText: String,
    val imageUrl: String? = null,
) {
    val imageId: Int
        get() {
            return (imageUrl?.let { R.drawable::class.java.getId(it) } ?: DEFAULT_IMAGE_ID)
        }

    companion object {
        const val DEFAULT_IMAGE_ID = R.drawable.ic_meetup
    }
}