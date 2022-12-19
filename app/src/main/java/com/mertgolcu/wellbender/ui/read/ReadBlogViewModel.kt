package com.mertgolcu.wellbender.ui.read

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.mertgolcu.wellbender.core.base.BaseViewModel
import com.mertgolcu.wellbender.domain.model.BlogWrite
import com.mertgolcu.wellbender.domain.model.BlogWriteCard
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Mert Gölcü on 19.12.2022.
 */

@HiltViewModel
class ReadBlogViewModel @Inject constructor() : BaseViewModel() {

    val title = MutableLiveData<String>()
    val currentPage = MutableLiveData(1)
    var totalPage = 0

    val cardList = MutableLiveData<List<BlogWriteCard>>()

    // temp
    val imageUrl = MutableLiveData(MOCK_BLOG_WRITE.imageUrl)

    override fun fetchExtras(extras: Bundle) {
        super.fetchExtras(extras)
        with(ReadBlogFragmentArgs.fromBundle(extras)) {
            fetchBlogWrite(blogWriteId)
        }
    }

    private fun fetchBlogWrite(id: String) {
        title.postValue(MOCK_BLOG_WRITE.title)
        totalPage = MOCK_BLOG_WRITE.pages?.size ?: 0
        val list = MOCK_BLOG_WRITE.pages?.sortedBy { it.pageNum } ?: listOf()
        cardList.postValue(list)
    }

    fun nextPage() {
        currentPage.value?.let {
            val newPageNum = it + 1
            if (newPageNum <= totalPage)
                currentPage.postValue(newPageNum)

        }
    }

    fun prevPage() {
        currentPage.value?.let {
            val newPageNum = it - 1
            if (newPageNum > 0)
                currentPage.postValue(newPageNum)
        }
    }

    companion object {
        val MOCK_BLOG_WRITE = BlogWrite(
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
                    pageNum = 2
                ),
                BlogWriteCard(
                    id = "2",
                    text = "When an unknown printer took a galley of type and scrambled it to make a type specimen book",
                    pageNum = 1
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
    }
}