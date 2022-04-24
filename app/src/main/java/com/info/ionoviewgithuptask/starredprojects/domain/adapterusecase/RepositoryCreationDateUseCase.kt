package com.info.ionoviewgithuptask.starredprojects.domain.adapterusecase

import android.annotation.SuppressLint
import com.info.ionoviewgithuptask.starredprojects.util.Constant.SIMPLE_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

class RepositoryCreationDateUseCase {
    @SuppressLint("SimpleDateFormat")
    operator fun invoke(calender:Calendar):String{
        val nCalender= calender.clone() as Calendar
        nCalender.add(Calendar.MONTH,-1)
        val formattedDate = SimpleDateFormat(SIMPLE_DATE_FORMAT).format(nCalender.getTime())
        return "created:>${formattedDate}"
    } }