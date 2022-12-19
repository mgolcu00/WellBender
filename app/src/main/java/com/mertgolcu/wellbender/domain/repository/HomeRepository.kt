package com.mertgolcu.wellbender.domain.repository

import com.mertgolcu.wellbender.domain.mock.*
import com.mertgolcu.wellbender.domain.model.Emotion
import com.mertgolcu.wellbender.domain.model.EmotionMood
import com.mertgolcu.wellbender.domain.model.UserPreferences
import com.mertgolcu.wellbender.domain.model.card.BaseCardModel
import com.mertgolcu.wellbender.domain.store.WellBenderDataStoreManager
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Mert Gölcü on 19.12.2022.
 */


interface IHomeRepository {
    suspend fun getUserData(): UserPreferences
    suspend fun getEmotionList(): List<Emotion>
    suspend fun setTodayEmotionMood(emotionMood: EmotionMood): Boolean
    suspend fun getTodayCard(): BaseCardModel
    suspend fun getBuyMoreCard(): BaseCardModel
    suspend fun getQuote(): String
}

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val dataStoreManager: WellBenderDataStoreManager
) : IHomeRepository {
    private var mock = true


    override suspend fun getUserData(): UserPreferences {
       /* if (mock)
            return mockUserData*/

        return dataStoreManager.userPreferencesFlow.first()
    }

    override suspend fun getEmotionList(): List<Emotion> {
        if (mock)
            return mockEmotionList
        return listOf()
    }

    override suspend fun setTodayEmotionMood(emotionMood: EmotionMood): Boolean {
        if (mock)
            return true
        return false
    }

    override suspend fun getTodayCard(): BaseCardModel {
        if (mock)
            return mockTodayCard

        return BaseCardModel("-", "-", "-")
    }

    override suspend fun getBuyMoreCard(): BaseCardModel {
        if (mock)
            return mockBuyMoreCard

        return BaseCardModel("-", "-", "-")
    }

    override suspend fun getQuote(): String {
        if (mock)
            return mockQuote

        return "-"
    }

}