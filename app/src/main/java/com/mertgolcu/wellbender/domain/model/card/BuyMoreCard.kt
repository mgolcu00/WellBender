package com.mertgolcu.wellbender.domain.model.card

/**
 * Created by Mert Gölcü on 18.12.2022.
 */

val mockBuyMoreCard =
    BaseCardModel(
        id = "1",
        title = "Plan Expired",
        description = "Get back blog access and session credits",
        button = CardButton(text = "Buy More"),
        imageUrl = "ic_health"
    )

val mockTodayCard =
    BaseCardModel(
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


val MOCK_TODAY_CARD = TodayCard(
    title = "Read Today",
    description = "Let’s open up to the things that matter the most",
    buttonText = "Read Now",
    imageUrl = "ic_health"
)
val MOCK_BUY_MORE = TodayCard(
    title = "Plan Expired",
    description = "Get back blog access and session credits",
    buttonText = "Buy More",
    imageUrl = "ic_cloud_download"
)