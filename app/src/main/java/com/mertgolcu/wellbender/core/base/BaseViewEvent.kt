package com.mertgolcu.wellbender.core.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.navigation.NavDirections

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

sealed class BaseViewEvent {
    data class Navigate(val directions: NavDirections) : BaseViewEvent()
    object NavigateBack : BaseViewEvent()
    data class NavigateBackWithData(
        @IdRes val destination: Int?,
        val result: Pair<String, Bundle>? = null
    ) : BaseViewEvent()

    data class ShowMessage(val message: String) : BaseViewEvent()
    data class ShowMessageWithRes(@StringRes val messageId: Int) : BaseViewEvent()
}