package com.mert.spaceflightnews.extension

import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

fun String.formatToDisplayDateTime(): String {
    return try {
        val instant = Instant.parse(this)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")
        localDateTime.format(formatter)
    } catch (e: Exception) {
        this
    }
}