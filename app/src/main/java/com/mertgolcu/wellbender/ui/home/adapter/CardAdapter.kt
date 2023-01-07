package com.mertgolcu.wellbender.ui.home.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.databinding.ItemHomeCardBinding
import com.mertgolcu.wellbender.domain.model.card.BaseCardModel
import com.mertgolcu.wellbender.domain.model.card.NewCardModel
import com.mertgolcu.wellbender.domain.model.card.NewCardType
import com.mertgolcu.wellbender.ext.getId
import com.mertgolcu.wellbender.ext.loadImage
import com.mertgolcu.wellbender.ext.loadImageFromLocal


/**
 * Created by Mert Gölcü on 7.01.2023.
 */

class CardAdapter : ListAdapter<NewCardModel, CardAdapter.CardViewHolder>(
    object : DiffUtil.ItemCallback<NewCardModel>() {
        override fun areItemsTheSame(oldItem: NewCardModel, newItem: NewCardModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: NewCardModel, newItem: NewCardModel) =
            oldItem.id == newItem.id

    }
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding =
            ItemHomeCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class CardViewHolder(private val binding: ItemHomeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewCardModel) {
            setType(item)
            setContent(item)
            setImage(item)
        }


        private fun setType(item: NewCardModel) {
            when (item.cardType) {
                NewCardType.GREEN.type -> {
                    setType(
                        backgroundColorId = R.color.night_green,
                        textColorId = R.color.white,
                        buttonColorId = R.color.soft_green
                    )
                }
                NewCardType.ORANGE.type -> {
                    setType(
                        backgroundColorId = R.color.light_orange,
                        textColorId = R.color.dark_brown,
                        buttonColorId = R.color.orange
                    )
                }
                NewCardType.BLUE.type -> {
                    setType(
                        backgroundColorId = R.color.gray,
                        textColorId = R.color.deep,
                        buttonColorId = R.color.purple_peach
                    )
                }
            }
        }

        private fun setType(backgroundColorId: Int, textColorId: Int, buttonColorId: Int) {
            // backgorund
            binding.layoutCard.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        binding.root.context,
                        backgroundColorId
                    )
                )

            // text Colors
            binding.textViewCardTitle.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    textColorId
                )
            )
            binding.textViewCardDescription.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    textColorId
                )
            )
            // button
            binding.buttonCard.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    binding.root.context,
                    buttonColorId
                )
            )
        }

        private fun setContent(item: NewCardModel) {
            binding.textViewCardTitle.text = item.title
            binding.textViewCardDescription.text = item.description
            //button
            binding.buttonCard.text = item.buttonText
            item.buttonIconId?.let {
                val id = (it.let { R.drawable::class.java.getId(it) })
                binding.buttonCard.setIconResource(id)
            }
        }

        private fun setImage(item: NewCardModel) {
            if (item.imageUrl != null) {
                binding.imageViewCard.loadImage(item.imageUrl)
            } else if (item.iconId != null) {
                val id = (item.iconId.let { R.drawable::class.java.getId(it) })
                binding.imageViewCard.loadImageFromLocal(id)
            } else {
                binding.imageViewCard.isVisible = false
            }
        }
    }
}