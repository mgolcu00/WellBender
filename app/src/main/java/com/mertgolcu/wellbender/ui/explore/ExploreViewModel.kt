package com.mertgolcu.wellbender.ui.explore

import androidx.lifecycle.MutableLiveData
import com.mertgolcu.wellbender.core.base.BaseViewModel
import com.mertgolcu.wellbender.domain.model.BlogWrite
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

@HiltViewModel
class ExploreViewModel @Inject constructor() : BaseViewModel() {

    val blogWriteList = MutableLiveData(MOCK_BLOG_WRITE_LIST)


    companion object {
        val MOCK_BLOG_WRITE_LIST = arrayListOf(
            BlogWrite(
                id = "1",
                title = "Test Title Now",
                description = null,
                readTime = 600L,
                publishDate = 1671315995462L,
                authorName = "mertgolcu",
                rawText = "Lorem ipsum dolor sit amet",
                imageUrl = "https://images.pexels.com/photos/14491698/pexels-photo-14491698.jpeg",
                pages = null,
                isBookMarked = false
            ),
            BlogWrite(
                id = "2",
                title = "Maybe its good title",
                description = null,
                readTime = 300000L,
                publishDate = 1671315995462L,
                authorName = "mertgolcu",
                rawText = "Lorem ipsum dolor sit amet",
                imageUrl = null,
                pages = null,
                isBookMarked = false
            ),
            BlogWrite(
                id = "3",
                title = "This get pages ASAP!",
                description = null,
                readTime = 900000L,
                publishDate = 1671315995462L,
                authorName = "mertgolcu",
                rawText = "Lorem ipsum dolor sit amet",
                imageUrl = "https://cdn.britannica.com/26/152026-050-41D137DE/Sunshine-leaves-beech-tree.jpg",
                pages = null,
                isBookMarked = false
            )
        )
    }
}