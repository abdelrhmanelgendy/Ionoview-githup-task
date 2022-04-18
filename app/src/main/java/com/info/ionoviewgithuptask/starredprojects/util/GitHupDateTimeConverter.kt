package com.info.ionoviewgithuptask.starredprojects.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun convertGitHupDateTimeToSimpleDate(date: String): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        val inputFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)

        val requiredFormat = DateTimeFormatter.ofPattern("dd-MM-yyy", Locale.ENGLISH)

        val inputDate = LocalDate.parse(date, inputFormatter)
        requiredFormat.format(inputDate)

    } else {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        val requiredFormat = SimpleDateFormat("dd-MM-yyy", Locale.ENGLISH)
        requiredFormat.format(simpleDateFormat.parse(date))
    }
}