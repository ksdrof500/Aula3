package com.aula.aula3.domain

import org.joda.time.DateTime
import org.joda.time.Days
import kotlin.math.absoluteValue

interface DateTransformer {
    fun dateToDateString(dateTime: DateTime): String
    fun getDifferenceOfDays(dateTime: DateTime): String
    fun isPast(launchDate: DateTime): Boolean
}

class DateTransformerImpl: DateTransformer {
    override fun dateToDateString(dateTime: DateTime): String {
        val date =
            "${dateTime.dayOfMonth().getStringValue()}-${dateTime.monthOfYear().getStringValue()}-${dateTime.yearOfEra().getStringValue()}"
        val time =
            "${dateTime.hourOfDay.getStringValue()}:${dateTime.minuteOfHour().getStringValue()}"
        return "$date at $time"
    }

    override fun getDifferenceOfDays(dateTime: DateTime): String {
        val daysDifference = Days.daysBetween(today(), dateTime)
        return daysDifference.days.absoluteValue.toString()
    }

    override fun isPast(launchDate: DateTime): Boolean =
        launchDate.isBefore(today())

    private fun DateTime.Property.getStringValue() = get().getTwoDigitsValue()

    private fun Int.getStringValue() = getTwoDigitsValue()

    private fun Int.getTwoDigitsValue() = this.toString().padStart(2, '0')

    private fun today(): DateTime = DateTime.now()
}
