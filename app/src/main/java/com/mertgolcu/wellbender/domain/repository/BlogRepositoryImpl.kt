package com.mertgolcu.wellbender.domain.repository

import com.mertgolcu.wellbender.domain.mock.mockBlogWriteList
import com.mertgolcu.wellbender.domain.model.BlogWrite
import javax.inject.Singleton

/**
 * Created by Mert Gölcü on 8.01.2023.
 */

interface IBlogRepository {
    suspend fun getFeaturedBlogWrites(): List<BlogWrite>
    suspend fun getBlogWrite(id: String): BlogWrite?
    suspend fun searchBlogWrites(query: String): List<BlogWrite>
}

@Singleton
class BlogRepositoryImpl : IBlogRepository {
    var mock = true
    override suspend fun getFeaturedBlogWrites(): List<BlogWrite> {
        if (mock) {
            return mockBlogWriteList
        }
        return listOf()
    }

    override suspend fun getBlogWrite(id: String): BlogWrite? {
        if (mock) {
            return mockBlogWriteList.find { it ->
                it.id == id
            }
        }
        return null
    }

    override suspend fun searchBlogWrites(query: String): List<BlogWrite> {
        return mockBlogWriteList.filter { bw ->
            bw.title.contains(
                other = query,
                ignoreCase = true
            )
        }
    }
}