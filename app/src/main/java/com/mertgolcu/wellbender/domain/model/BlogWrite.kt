package com.mertgolcu.wellbender.domain.model

import android.webkit.URLUtil
import com.mertgolcu.wellbender.ext.formatDate
import com.mertgolcu.wellbender.ext.formatSecondToMeaning

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
            return readTime?.formatSecondToMeaning().toString()
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

val MOCK_BLOG_WRITE_1 = BlogWrite(
    id = "0",
    title = "What is Lorem Ipsum?",
    description = null,
    readTime = 180L,
    publishDate = 1671315995462L,
    authorName = "mertgolcu",
    rawText = "What is Lorem Ipsum?\n" +
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
    imageUrl = null,//"https://cdn.pixabay.com/photo/2013/12/17/20/10/bubbles-230014__340.jpg",
    pages = listOf(
        BlogWriteCard(
            id = "0",
            text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            pageNum = 0
        ),
        BlogWriteCard(
            id = "1",
            text = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
            pageNum = 1
        ),
        BlogWriteCard(
            id = "2",
            text = "When an unknown printer took a galley of type and scrambled it to make a type specimen book",
            pageNum = 2
        ),
        BlogWriteCard(
            id = "3",
            text = "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged",
            pageNum = 3
        ),
        BlogWriteCard(
            id = "4",
            text = "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages",
            pageNum = 4
        ),
        BlogWriteCard(
            id = "5",
            text = "And more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            pageNum = 5
        ),

        ),
    isBookMarked = false
)

val MOCK_BLOG_WRITE_2 = BlogWrite(
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
)
val MOCK_BLOG_WRITE_3 = BlogWrite(
    id = "2",
    title = "Maybe its good title",
    description = null,
    readTime = 300000L,
    publishDate = 1671315009999L,
    authorName = "mertgolcu",
    rawText = "Lorem ipsum dolor sit amet",
    imageUrl = null,
    pages = null,
    isBookMarked = false
)
val MOCK_BLOG_WRITE_4 = BlogWrite(
    id = "3",
    title = "This get pages ASAP!",
    description = null,
    readTime = 900000L,
    publishDate = 1671315995462L,
    authorName = "mertgolcu",
    rawText = "Lorem ipsum dolor sit amet",
    imageUrl = "https://cdn.pixabay.com/photo/2013/12/17/20/10/bubbles-230014__340.jpg",
    pages = null,
    isBookMarked = false
)