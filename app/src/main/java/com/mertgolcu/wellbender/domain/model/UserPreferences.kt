package com.mertgolcu.wellbender.domain.model

/**
 * Created by Mert Gölcü on 16.12.2022.
 */

data class UserPreferences(
    val name: String,
    val hasStartUp: Boolean = false,
    val avatarUrl: String = ""
)