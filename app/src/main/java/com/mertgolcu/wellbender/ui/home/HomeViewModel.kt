package com.mertgolcu.wellbender.ui.home

import androidx.lifecycle.MutableLiveData
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

    val userName = MutableLiveData(MOCK_USER_NAME)
    val welcomeMessage = MutableLiveData(MOCK_WELCOME_MESSAGE)
    val avatarUrl = MutableLiveData(MOCK_AVATAR_URL)
    val emojiList = MutableLiveData(MOCK_EMOTION_LIST)

    companion object {
        const val MOCK_USER_NAME = "Mert"
        const val MOCK_WELCOME_MESSAGE = "Good Afternoon,"
        const val MOCK_AVATAR_URL = "https://avatars2.githubusercontent.com/u/44591905"
        val MOCK_EMOTION_LIST = arrayListOf(
            MockEmotion("Happy", R.drawable.ic_happy_emoji),
            MockEmotion("Happy", R.drawable.ic_happy_emoji),
            MockEmotion("Happy", R.drawable.ic_happy_emoji),
        )
    }
}

data class MockEmotion(
    val text: String,
    val image: Int,
    val imageUrl: String? = null
)