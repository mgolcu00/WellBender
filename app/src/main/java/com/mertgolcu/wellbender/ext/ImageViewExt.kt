package com.mertgolcu.wellbender.ext

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.mertgolcu.wellbender.R

/**
 * Created by Mert Gölcü on 16.12.2022.
 */

const val PLACEHOLDER_ICON = R.drawable.ic_person
const val PLACEHOLDER_LOADING = R.drawable.ic_cloud_download

fun AppCompatImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(PLACEHOLDER_ICON)
        .into(this)
}

fun AppCompatImageView.loadCircleImage(url: String) {
    Glide.with(this)
        .load(url)
        .circleCrop()
        .placeholder(PLACEHOLDER_LOADING)
        .into(this)
}