package com.mertgolcu.wellbender.ext

import android.annotation.SuppressLint
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * Created by Mert Gölcü on 28.12.2022.
 */

fun Long.formatSecondToMeaning(): String {
    var text = ""

    val d = Duration.ZERO.plusSeconds(this)
    val h = d.seconds / 3600
    val m = (d.seconds % 3600) / 60 //(s % 3600) / 60
    val s = (d.seconds % (3600 / 60))
    if (h > 0) text += "$h h "
    if (m > 0) text += "$m min "
    if (s > 0L) {
        text += "$s sec"
    }

    return text
}

fun Long.formatMillisToMinAndSec(): String {
    val seconds = ((this / 1000.0) % 60.0).toInt()
    val minutes = (TimeUnit.MILLISECONDS.toMinutes(this) % 60).toInt()
   // val formatSeconds = DecimalFormat("00").format(seconds)
    return String.format(Locale.US, "%02d:%02d", minutes,seconds)
}

@SuppressLint("SimpleDateFormat")
fun Long.formatDate(pattern: String = "dd MM yyyy"): String {
    return try {
        val sdf = SimpleDateFormat(pattern)
        sdf.format(Date(this))
    } catch (e: Exception) {
        e.printStackTrace()
        "cannot"
    }
}

