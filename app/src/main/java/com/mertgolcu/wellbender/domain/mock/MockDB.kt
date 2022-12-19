package com.mertgolcu.wellbender.domain.mock

import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.domain.model.Emotion
import com.mertgolcu.wellbender.domain.model.UserPreferences
import com.mertgolcu.wellbender.domain.model.card.BaseCardModel
import com.mertgolcu.wellbender.domain.model.card.CardButton

/**
 * Created by Mert Gölcü on 19.12.2022.
 */

val mockBuyMoreCard = BaseCardModel(
    id = "1",
    title = "Plan Expired",
    description = "Get back blog access and session credits",
    button = CardButton(
        text = "Buy More",
        backgroundTintColor = "soft_green",
        icon = "ic_home",
        textColor = "white",
        iconColor = "white"
    ),
    imageUrl = "ic_health",
    backgroundTintColor = "night_green",
    textColor = "white"
).also {
    it.imageTintColorId = R.color.soft_green
}

val mockTodayCard = BaseCardModel(
    id = "2",
    title = "Read Today",
    description = "Let’s open up to the things that matter the most",
    button = CardButton(
        text = "Read Now",
        backgroundTintColor = "orange",
        icon = "ic_calendar",
        textColor = "white",
        iconColor = "white"
    ),
    imageUrl = "ic_meetup",
    backgroundTintColor = "light_orange",
    textColor = "dark_brown",
).also {
    it.imageTintColorId = null
}

val mockEmotionList = listOf(
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

val mockQuote =
    "“It is better to conquer yourself than to win a thousand battles lorem ipsum dolor sit amet vela dust” - Mark Zuckerberg"

val mockUserData =
    UserPreferences(
        name = "Jhon",
        hasStartUp = false,
        avatarUrl = "https://avatars2.githubusercontent.com/u/44591905"
    )
