package com.mertgolcu.wellbender.core.binding

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.mertgolcu.wellbender.ext.loadCircleImage

/**
 * Created by Mert Gölcü on 18.12.2022.
 */

@BindingAdapter("app:visible")
fun visible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}