package com.mertgolcu.wellbender.domain.mock

import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.domain.model.Emotion
import com.mertgolcu.wellbender.domain.model.MOCK_BLOG_WRITE_1
import com.mertgolcu.wellbender.domain.model.MOCK_BLOG_WRITE_2
import com.mertgolcu.wellbender.domain.model.MOCK_BLOG_WRITE_3
import com.mertgolcu.wellbender.domain.model.MOCK_BLOG_WRITE_4
import com.mertgolcu.wellbender.domain.model.UserPreferences
import com.mertgolcu.wellbender.domain.model.card.BaseCardModel
import com.mertgolcu.wellbender.domain.model.card.CardButton
import com.mertgolcu.wellbender.domain.model.card.MOCK_NEW_CARD_MODEL_1
import com.mertgolcu.wellbender.domain.model.card.MOCK_NEW_CARD_MODEL_2
import com.mertgolcu.wellbender.domain.model.card.MOCK_NEW_CARD_MODEL_3
import com.mertgolcu.wellbender.domain.model.card.MOCK_NEW_CARD_MODEL_4

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

/*
<color name="iris">#4754F0</color>
    <color name="purple_peach">#AE94FB</color>
    <color name="peach">#FFCCB7</color>
    <color name="onyx">#29303E</color>
    <color name="deep">#0E0E2C</color>
    <color name="gray">#BAB9D0</color>
    <color name="dust">#E0DFE9</color>
    <color name="light">#F9F9F9</color>
    <color name="whitish">#FBFBFB</color>


    <color name="orange">#FE8235</color>
    <color name="soft_orange">#F09E54</color>
    <color name="light_orange">#FEF3E7</color>
    <color name="dark_brown">#573926</color>
    <color name="night_green">#55A06F</color>
    <color name="soft_green">#99D9AF</color>
 */
val mockEmotionList = listOf(
    Emotion(
        id = "1",
        title = "Happy",
        iconUrl = "ic_emoji_happy",
        color = "orange"
    ),
    Emotion(
        id = "2",
        title = "Sad",
        iconUrl = "ic_emoji_sad",
        color = "dark_brown"
    ),
    Emotion(
        id = "3",
        title = "Anxious",
        iconUrl = "ic_emoji_anxious",
        color = "night_green",
    ),
    Emotion(
        id = "4",
        title = "Angry",
        iconUrl = "ic_emoji_angry",
        color = "peach",
    ),
    Emotion(
        id = "5",
        title = "Dizzy",
        iconUrl = "ic_emoji_dizzy",
        color = "soft_orange",
    ),
    Emotion(
        id = "6",
        title = "Love",
        iconUrl = "ic_emoji_love",
        color = "soft_green",
    ),
    Emotion(
        id = "7",
        title = "Notr",
        iconUrl = "ic_emoji_notr",
        color = "dust",
    ),
    Emotion(
        id = "8",
        title = "Tired",
        iconUrl = "ic_emoji_tired",
        color = "purple_peach",
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

val newMockCardList = arrayListOf(
    MOCK_NEW_CARD_MODEL_1,
    MOCK_NEW_CARD_MODEL_2,
    MOCK_NEW_CARD_MODEL_3,
    MOCK_NEW_CARD_MODEL_4
)

val mockBlogWriteList = arrayListOf(
    MOCK_BLOG_WRITE_1,
    MOCK_BLOG_WRITE_2,
    MOCK_BLOG_WRITE_3,
    MOCK_BLOG_WRITE_4,
)
