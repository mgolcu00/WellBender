package com.mertgolcu.wellbender.ext

import android.text.format.DateFormat
import com.google.android.material.timepicker.TimeFormat
import java.sql.Time
import java.time.Duration
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.TemporalAccessor
import java.util.Date
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.seconds


/**
 * Created by Mert Gölcü on 16.12.2022.
 */


// get resources id from resources name
inline fun <reified T : Class<*>> T.getId(resourceName: String): Int {
    return try {
        val idField = getDeclaredField(resourceName)
        idField.getInt(idField)
    } catch (e: Exception) {
        e.printStackTrace()
        -1
    }
}

fun Long.formatTimeToMeaning(): String {
    var text = ""
/*    val time = Time(this)

    text += "${time.hours} h"
    text += "${time.minutes} min"
    text += "${time.seconds} sec"
*/
    val duration = Duration.ZERO.plusSeconds(this)
    var h = duration.seconds / 3600
    var m = (duration.seconds % 3600) / 60 //(s % 3600) / 60

    if (h > 0)
        text += "$h h "
    if (m > 0)
        text += "$m min "
    return text
    return DateTimeFormatter.ofPattern("hh").format(Date(this).toInstant())
    val formattedTime =
        DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL).format(Date(this).toInstant())
    return formattedTime ?: "none"
}

fun Long.formatDate(format: String = "dd MM yyyy"): String {
    try {
//        return DateTimeFormatter.ofPattern(format).format(Date(this).toInstant())
        val formattedDate =
            DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(Date(this).toInstant())
        return formattedDate ?: "none"
    } catch (e: Exception) {
       // e.printStackTrace()
        return "cannot"
    }
}
