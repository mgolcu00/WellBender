package com.mertgolcu.wellbender.core.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

abstract class BaseViewModel : ViewModel() {

    private val _baseEventChannel = Channel<BaseViewEvent>()

    // Kotlin Flow Channel
    val baseEvent = _baseEventChannel.receiveAsFlow()
    /*
    * Use kotlin Flow and Channel because Flow has observable fluid data like events
    * and Channel: this observable data redirect a channel to observe them one scope
    * */

    // Navigation
    fun navigate(directions: NavDirections) = viewModelScope.launch {
        _baseEventChannel.send(BaseViewEvent.Navigate(directions))
    }

    fun popBackStack() = viewModelScope.launch {
        _baseEventChannel.send(BaseViewEvent.NavigateBack)
    }

    fun navigateBackWithResult(
        @IdRes destination: Int? = null,
        result: Bundle,
        requestKey: String
    ) = viewModelScope.launch {
        _baseEventChannel.send(
            BaseViewEvent.NavigateBackWithData(
                destination,
                Pair(requestKey, result)
            )
        )
    }

    @CallSuper
    open fun fetchExtras(extras: Bundle) {
    }

    // Message
    fun showMessage(message: String) = viewModelScope.launch {
        _baseEventChannel.send(BaseViewEvent.ShowMessage(message))
    }

    fun showMessageWithRes(@StringRes messageId: Int) = viewModelScope.launch {
        _baseEventChannel.send(BaseViewEvent.ShowMessageWithRes(messageId))
    }

}