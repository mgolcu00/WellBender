package com.mertgolcu.wellbender.ui.home

import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.IconCompat.IconType
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseViewModel
import com.mertgolcu.wellbender.domain.model.Emotion
import com.mertgolcu.wellbender.domain.store.WellBenderDataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreManager: WellBenderDataStoreManager
) : BaseViewModel() {

    // welcome
    val userName = MutableLiveData(MOCK_USER_NAME)
    val welcomeMessage = MutableLiveData(MOCK_WELCOME_MESSAGE)
    val avatarUrl = MutableLiveData(MOCK_AVATAR_URL)

    // emotion emojis
    val emojiList = MutableLiveData(EMOTION_LIST)

    // today card
    val todayCardTitle = MutableLiveData(MOCK_TODAY_CARD.title)
    val todayCardDescription = MutableLiveData(MOCK_TODAY_CARD.description)
    val todayCardButtonText = MutableLiveData(MOCK_TODAY_CARD.buttonText)
    val todayCardImageUrl = MutableLiveData(MOCK_TODAY_CARD.imageUrl ?: MOCK_TODAY_CARD.imageId)


    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        viewModelScope.launch {
            dataStoreManager.userPreferencesFlow.collectLatest {
                userName.postValue(it.name)
                avatarUrl.postValue(it.avatarUrl)
            }
        }
    }


    companion object {
        const val MOCK_USER_NAME = "NO NAME MOCK"
        const val MOCK_WELCOME_MESSAGE = "Good Afternoon,"
        const val MOCK_AVATAR_URL = "https://avatars2.githubusercontent.com/u/44591905"
        val MOCK_EMOTION_LIST = arrayListOf(
            MockEmotion("Happy", R.drawable.ic_happy_emoji),
            MockEmotion("Happy", R.drawable.ic_happy_emoji, color = R.color.deep),
            MockEmotion("Happy", R.drawable.ic_happy_emoji, color = R.color.peach),
            MockEmotion("Happy", R.drawable.ic_happy_emoji, color = R.color.orange),
            MockEmotion("Happy", R.drawable.ic_happy_emoji, color = R.color.iris),
            MockEmotion("Happy", R.drawable.ic_happy_emoji, color = R.color.onyx),
            MockEmotion("Happy", R.drawable.ic_happy_emoji),
        )
        val MOCK_TODAY_CARD = MockTodayCard(
            title = "Read Today",
            description = "Let’s open up to the things that matter the most",
            buttonText = "Read Now",
        )
        val EMOTION_LIST = arrayListOf(
            Emotion(
                id = "1",
                title = "Happy",
                iconUrl = "ic_happy_emoji",
                color = "orange",
                iconTint = "light"
            ),
            Emotion(
                id = "2",
                title = "Calm",
                iconUrl = "https://img.icons8.com/emoji/48/null/relieved-face.png",
                color = "#007FAC",
                iconTint = "#ff00ff"
            ),
            Emotion(
                id = "3",
                title = "Angry",
                iconUrl = "https://img.icons8.com/emoji/48/null/angry-face-emoji--v2.png",
                color = "purple_peach"
            ),
            Emotion(
                id = "4",
                title = "Sad",
                iconUrl = "https://img.icons8.com/emoji/48/null/weary-face.png",
                color = "#181D31"
            ),
            Emotion(
                id = "5",
                title = "Manic",
                iconUrl = "https://img.icons8.com/emoji/48/null/clincking-glasses.png",
                color = "deep"
            ),
            Emotion(
                id = "6",
                title = "Social",
                iconUrl = "ic_meetup",
                color = "light_orange"
            ),
        )
    }
}

data class MockEmotion(
    val text: String,
    val image: Int,
    val imageUrl: String? = null,
    val color: Int? = R.color.purple_peach
)

data class MockTodayCard(
    val title: String,
    val description: String,
    val buttonText: String,
    val imageUrl: String? = null,
    val imageId: Int = R.drawable.ic_meetup
)