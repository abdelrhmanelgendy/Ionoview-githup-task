package com.info.ionoviewgithuptask.starredprojects.domain.adapterusecase

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.powermock.api.mockito.PowerMockito
import java.text.SimpleDateFormat
import java.util.*

@RunWith(JUnit4::class)
class RepositoryCreationDateUseCaseTest {
    @Test
    fun test_creating_repository_creation_date_with_calender_returns_calender_minus_one_month() {
        PowerMockito.mockStatic(Calendar::class.java)
        val calendarInstance = getCalender()
        val repositoryCreationDateUseCase =
            RepositoryCreationDateUseCase()(calender = calendarInstance!!)

        assertEquals(
            repositoryCreationDateUseCase,
            "created:>${calendarInstance.get(Calendar.YEAR)}-${
                formatMonthIfContainsSingleDigits(
                    calendarInstance.get(Calendar.MONTH)
                )
            }-${
                formatMonthIfContainsSingleDigits(
                    calendarInstance.get(
                        Calendar.DAY_OF_MONTH
                    )
                )
            }"
        )

    }

    private fun getCalender(): Calendar? {
        return Calendar.getInstance()
    }

    private fun formatMonthIfContainsSingleDigits(month: Int): String {
        return when (month.toString().length) {
            1 -> {
                "0$month"
            }
            else -> {
                month.toString()
            }
        }

    }
}