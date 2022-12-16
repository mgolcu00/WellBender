package com.mertgolcu.wellbender.core.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.mertgolcu.wellbender.ext.loadCircleImage
import com.mertgolcu.wellbender.ext.loadImage

/**
 * Created by Mert Gölcü on 16.12.2022.
 */

@BindingAdapter("app:loadImage")
fun loadImage(imageView: AppCompatImageView, url: String) {
    imageView.loadImage(url)
}

@BindingAdapter("app:loadCircleImage")
fun loadCircleImage(imageView: AppCompatImageView, url: String) {
    imageView.loadCircleImage(url)
}