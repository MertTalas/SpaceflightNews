package com.mert.spaceflightnews.extension

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun String?.formatToDisplayDateTime(): String {
    if (this.isNullOrEmpty()) {
        return "Date Info Not Available"
    }

    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.ENGLISH).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

        val date = try {
            parser.parse(this)
        } catch (e: Exception) {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }.parse(this)
        } ?: return "Invalid Date Format"

        val formatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.ENGLISH).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

        formatter.format(date)

    } catch (e: Exception) {
        "Invalid Date Format"
    }
}