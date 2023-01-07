package com.mertgolcu.wellbender.domain.model.card

/**
 * Created by Mert Gölcü on 7.01.2023.
 */

data class NewCardModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String? = null,
    val iconId: String? = null,
    val buttonText: String,
    val buttonIconId: String?,
    val cardType: String,
    val coldAction: String,
    val rawParamsKeys: List<String>? = null,
    val rawParamsValues: List<String>? = null,
    val isVisible: Boolean
) {

    val params: HashMap<String, String>?
        get() {
            if (!rawParamsKeys.isNullOrEmpty() && !rawParamsValues.isNullOrEmpty()) {

                val map = HashMap<String, String>(rawParamsKeys.size)
                rawParamsKeys.forEachIndexed { i, v ->
                    map[v] = rawParamsValues[i]
                }
                return map
            }
            return null
        }
}

enum class NewCardType(val type: String) {
    ORANGE("orange"),
    GREEN("green"),
    BLUE("blue")
}

enum class NewCardColdAction(val action: String) {
    GO_BLOG("go_blog"),
    GO_SOUND("go_sound"),
    GO_A_BLOG_WRITE("go_a_blog_write"),
    GO_A_SOUND("go_a_sound"),
    BUY_MORE("buy_more"),

}

val MOCK_NEW_CARD_MODEL_1 =
    NewCardModel(
        id = "0",
        title = "Example Title",
        description = "Example Description",
        imageUrl = "https://picsum.photos/300",
        iconId = null,
        buttonText = "Example Do",
        buttonIconId = "ic_calendar",
        cardType = NewCardType.GREEN.type,
        coldAction = NewCardColdAction.GO_BLOG.action,
        isVisible = true
    )

val MOCK_NEW_CARD_MODEL_2 =
    NewCardModel(
        id = "1",
        title = "Example Title",
        description = "Example Description",
        imageUrl = null,
        iconId = "ic_meetup",
        buttonText = "Example Do",
        buttonIconId = null,
        cardType = NewCardType.ORANGE.type,
        coldAction = NewCardColdAction.GO_SOUND.action,
        isVisible = true
    )

val MOCK_NEW_CARD_MODEL_3 =
    NewCardModel(
        id = "2",
        title = "New Sound Released",
        description = "Example Description Relaxing And Different",
        imageUrl = null,
        iconId = null,
        buttonText = "Example Do",
        buttonIconId = "ic_play_circle",
        cardType = NewCardType.BLUE.type,
        coldAction = NewCardColdAction.GO_A_SOUND.action,
        rawParamsKeys = listOf("sound_id"),
        rawParamsValues = listOf("1"),
        isVisible = true
    )

val MOCK_NEW_CARD_MODEL_4 =
    NewCardModel(
        id = "3",
        title = "Buy More Title",
        description = "Buy More because why not example description",
        imageUrl = null,
        iconId = "ic_health",
        buttonText = "Buy More",
        buttonIconId = "ic_done",
        cardType = NewCardType.GREEN.type,
        coldAction = NewCardColdAction.BUY_MORE.action,
        isVisible = true
    )