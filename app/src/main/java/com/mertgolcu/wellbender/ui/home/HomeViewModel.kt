package com.mertgolcu.wellbender.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mertgolcu.wellbender.core.base.BaseViewModel
import com.mertgolcu.wellbender.domain.model.Emotion
import com.mertgolcu.wellbender.domain.model.EmotionMood
import com.mertgolcu.wellbender.domain.model.card.BaseCardModel
import com.mertgolcu.wellbender.domain.repository.HomeRepositoryImpl
import com.mertgolcu.wellbender.domain.repository.IHomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepositoryImpl
) : BaseViewModel() {

    // welcome
    val userName = MutableLiveData<String>()
    val welcomeMessage = MutableLiveData(MOCK_WELCOME_MESSAGE)
    val avatarUrl = MutableLiveData<String>()

    // emotion emojis
    val emojiList = MutableLiveData<List<Emotion>>()
    val todayEmotionMood = MutableLiveData<EmotionMood>()

    // today card
    val todayCard = MutableLiveData<BaseCardModel>()

    // buy more card
    val buyMoreCard = MutableLiveData<BaseCardModel>()

    // quote
    val quote = MutableLiveData<String>()

    init {
        fetchUserData()
        fetchEmotionList()
        fetchTodayCard()
        fetchBuyMoreCard()
        fetchQuote()
    }


    private fun fetchUserData() {
        viewModelScope.launch {
            homeRepository.getUserData().let {
                userName.postValue(it.name)
                avatarUrl.postValue(it.avatarUrl)
            }
        }
    }

    private fun fetchEmotionList() {
        viewModelScope.launch {
            homeRepository.getEmotionList().let {
                emojiList.postValue(it)
            }
        }
    }

    private fun fetchTodayCard() {
        viewModelScope.launch {
            homeRepository.getTodayCard().let {
                todayCard.postValue(it)
            }
        }
    }

    private fun fetchBuyMoreCard() {
        viewModelScope.launch {
            homeRepository.getBuyMoreCard().let {
                buyMoreCard.postValue(it)
            }
        }
    }

    private fun fetchQuote() {
        viewModelScope.launch {
            homeRepository.getQuote().let {
                quote.postValue(it)
            }
        }
    }

    fun setTodayEmotionMood(emotion: Emotion) {
        val mood = EmotionMood(
            id = "1",
            emotion = emotion,
            date = Calendar.getInstance().timeInMillis
        )
        viewModelScope.launch {
            homeRepository.setTodayEmotionMood(mood).let {
                if (it) {
                    todayEmotionMood.postValue(mood)
                }
            }
        }
    }


    companion object {
        const val MOCK_USER_NAME = "NO NAME MOCK"
        const val MOCK_WELCOME_MESSAGE = "Good Afternoon,"
        const val MOCK_AVATAR_URL = "https://avatars2.githubusercontent.com/u/44591905"

        const val MOCK_QUOTE =
            "“It is better to conquer yourself than to win a thousand battles lorem ipsum dolor sit amet vela dust” - Mark Zuckerberg"
    }
}