package com.mertgolcu.wellbender.ui.read.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mertgolcu.wellbender.databinding.ItemBlogReadCardBinding
import com.mertgolcu.wellbender.databinding.ItemBlogWriteBinding
import com.mertgolcu.wellbender.domain.model.BlogWriteCard

/**
 * Created by Mert Gölcü on 19.12.2022.
 */

class BlogCardAdapter : ListAdapter<BlogWriteCard, BlogCardAdapter.BlogCardViewHolder>(
    object : DiffUtil.ItemCallback<BlogWriteCard>() {
        override fun areItemsTheSame(oldItem: BlogWriteCard, newItem: BlogWriteCard) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: BlogWriteCard, newItem: BlogWriteCard) =
            oldItem.id == newItem.id

    }
) {


    inner class BlogCardViewHolder(private val binding: ItemBlogReadCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BlogWriteCard) {
            binding.textViewWrite.text = item.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogCardViewHolder {
        val binding =
            ItemBlogReadCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return BlogCardViewHolder(binding)

    }

    override fun onBindViewHolder(holder: BlogCardViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}