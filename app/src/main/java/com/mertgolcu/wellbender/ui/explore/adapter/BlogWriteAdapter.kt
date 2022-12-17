package com.mertgolcu.wellbender.ui.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mertgolcu.wellbender.databinding.ItemBlogWriteBinding
import com.mertgolcu.wellbender.databinding.ItemEmotionBinding
import com.mertgolcu.wellbender.domain.model.BlogWrite
import com.mertgolcu.wellbender.domain.model.Emotion
import com.mertgolcu.wellbender.ext.loadImage

/**
 * Created by Mert Gölcü on 18.12.2022.
 */

class BlogWriteAdapter : ListAdapter<BlogWrite, BlogWriteAdapter.BlogWriteViewHolder>(
    object : DiffUtil.ItemCallback<BlogWrite>() {
        override fun areItemsTheSame(oldItem: BlogWrite, newItem: BlogWrite) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: BlogWrite, newItem: BlogWrite) =
            oldItem.id == newItem.id

    }
) {

    var onClickRead: ((item: BlogWrite) -> Unit)? = null
    var onClickBookmark: ((item: BlogWrite, mark: Boolean) -> Unit)? = null

    inner class BlogWriteViewHolder(private val binding: ItemBlogWriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var isBookMarked = false

        fun bind(item: BlogWrite) {
            binding.apply {
                textViewTitle.text = item.title
                textViewAuthor.text = item.authorName
                textViewPublishDate.text = item.formattedPublishDate
                textViewReadTime.text = item.formattedReadTime

                imageViewHeader.isVisible = item.isHaveImage
                item.imageUrl?.let { imageViewHeader.loadImage(it, true) }

                isBookMarked = item.isBookMarked
                buttonRead.setOnClickListener {
                    onClickRead?.invoke(item)
                }
                buttonBookmark.setOnClickListener {
                    onClickBookmark?.invoke(item, isBookMarked)
                    isBookMarked = !isBookMarked
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogWriteViewHolder {
        val binding =
            ItemBlogWriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BlogWriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BlogWriteViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}