package com.mertgolcu.wellbender.ext

/**
 * Created by Mert Gölcü on 16.12.2022.
 */

fun getResourceId(name: String, c: Class<*>): Int = try {
    val field = c.getDeclaredField(name)
    field.getInt(field)
} catch (e: Exception) {
    -1
}

inline fun <reified T : Class<*>> T.getId(resourceName: String): Int {
    return try {
        val idField = getDeclaredField(resourceName)
        idField.getInt(idField)
    } catch (e: Exception) {
        e.printStackTrace()
        -1
    }
}