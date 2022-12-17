package com.mertgolcu.wellbender.domain.model

import android.webkit.URLUtil
import com.mertgolcu.wellbender.ext.formatDate
import com.mertgolcu.wellbender.ext.formatTimeToMeaning

/**
 * Created by Mert Gölcü on 18.12.2022.
 */

data class BlogWrite(
    val id: String,
    val title: String,
    val description: String? = null,
    val readTime: Long? = null, // read time as miliseconds
    val publishDate: Long,
    val authorName: String,
    val rawText: String,
    val pages: List<BlogWriteCard>? = null,
    val imageUrl: String?,
    val isBookMarked: Boolean = false
) {
    val formattedReadTime: String
        get() {
            return readTime?.formatTimeToMeaning().toString()
        }

    val formattedPublishDate: String
        get() {
            return publishDate.formatDate()
        }

    val isHaveImage: Boolean
        get() = imageUrl != null && URLUtil.isValidUrl(imageUrl)


}

data class BlogWriteCard(
    val id: String,
    val text: String,
    val pageNum: Int,
)