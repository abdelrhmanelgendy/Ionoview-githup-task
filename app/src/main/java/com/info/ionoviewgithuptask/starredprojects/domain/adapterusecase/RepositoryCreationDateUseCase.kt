package com.info.ionoviewgithuptask.starredprojects.domain.adapterusecase

import java.text.SimpleDateFormat
import java.util.*

class RepositoryCreationDateUseCase {
    operator fun invoke(calender:Calendar):String{
        calender.add(Calendar.MONTH,-1)
        val formattedDate = SimpleDateFormat("yyyy-MM-dd").format(calender.getTime())
        return "created:>${formattedDate}"
    } }