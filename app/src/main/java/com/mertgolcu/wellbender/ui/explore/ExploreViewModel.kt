package com.mertgolcu.wellbender.ui.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mertgolcu.wellbender.core.base.BaseViewModel
import com.mertgolcu.wellbender.domain.model.BlogWrite
import com.mertgolcu.wellbender.domain.repository.BlogRepositoryImpl
import com.mertgolcu.wellbender.domain.repository.IBlogRepository
import com.mertgolcu.wellbender.ui.main.MainFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val blogRepository: IBlogRepository
) : BaseViewModel() {

    val blogWriteList = MutableLiveData<List<BlogWrite>>()
    val query = MutableLiveData("")

    init {
        fetchFeaturedBlogs()
    }

    private fun fetchFeaturedBlogs() {
        viewModelScope.launch {
            blogRepository.getFeaturedBlogWrites().let {
                blogWriteList.postValue(it)
            }
        }
    }

    fun searchBlogs(query: String) {
        viewModelScope.launch {
            if (query.isNotEmpty())
                blogRepository.searchBlogWrites(query).let {
                    blogWriteList.postValue(it)
                }
            else
                blogRepository.getFeaturedBlogWrites().let {
                    blogWriteList.postValue(it)
                }
        }
    }

    fun goToBlogWrite(bw: BlogWrite) {
        navigate(MainFragmentDirections.actionMainFragmentToReadBlogFragment(bw.id))
    }

    companion object {
        val MOCK_BLOG_WRITE_LIST = listOf(
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
                imageUrl = "https://cdn.pixabay.com/photo/2013/12/17/20/10/bubbles-230014__340.jpg",
                pages = null,
                isBookMarked = false
            )
        )
    }
}