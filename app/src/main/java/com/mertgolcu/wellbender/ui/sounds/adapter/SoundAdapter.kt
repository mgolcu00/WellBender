package com.mertgolcu.wellbender.ui.sounds.adapter

import android.content.res.ColorStateList
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.binding.loadImage
import com.mertgolcu.wellbender.databinding.ItemSoundHorizontalBinding
import com.mertgolcu.wellbender.domain.model.BlogWrite
import com.mertgolcu.wellbender.domain.model.Sound
import com.mertgolcu.wellbender.ext.dp
import com.mertgolcu.wellbender.ext.px

/**
 * Created by Mert Gölcü on 18.12.2022.
 */

class SoundAdapter : ListAdapter<Sound, SoundAdapter.SoundViewHolder>(
    object : DiffUtil.ItemCallback<Sound>() {
        override fun areItemsTheSame(oldItem: Sound, newItem: Sound) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Sound, newItem: Sound) =
            oldItem.id == newItem.id

    }
) {

    var onClickPlay: ((item: BlogWrite) -> Unit)? = null

    inner class SoundViewHolder(private val binding: ItemSoundHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Sound) {
            binding.apply {
                loadImage(imageViewSoundImage, item.imageUrl)

                textViewTitle.text = item.title
                textViewTime.text = item.formattedDuration

                createTags(item)
            }
        }

        private fun createTags(item: Sound) {
            val tags = item.tags
            tags?.let { list ->
                if (list.isNotEmpty()) binding.layoutTags.isVisible = true
                list.forEach {
                    val textView = TextView(
                        ContextThemeWrapper(binding.root.context, R.style.Text_Body)
                    )
                    val params = LinearLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT
                    )
                    params.weight = 1F
                    params.marginStart = 4.px
                    textView.layoutParams = params

                    textView.maxLines=1

                    textView.text = it.name
                    textView.background = ResourcesCompat.getDrawable(
                        binding.root.resources,
                        R.drawable.rounded_background_8dp,
                        binding.root.context.theme
                    )

                    textView.setPadding(4.px)


                    textView.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            binding.root.context,
                            it.colorId ?: R.color.soft_green
                        )
                    )
                    // add to layout
                    binding.layoutTags.addView(textView)

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {
        val binding =
            ItemSoundHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SoundViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SoundViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}