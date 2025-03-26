package com.jmenmar.ikasi.presentation.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

fun todayInstant(): Instant =
    Instant.fromEpochMilliseconds(Clock.System.now().toEpochMilliseconds())

fun beforeDateInstant(value: Int, unit: DateTimeUnit): Instant =
    Clock.System.now().minus(value, unit, TimeZone.currentSystemDefault())

fun beforeDate(value: Int, unit: DateTimeUnit): Int =
    beforeDateInstant(value = value, unit = unit)
        .toLocalDate().toEpochDays()

fun todayLocalDate(): LocalDate =
    todayInstant().toLocalDate()

fun Instant.toLocalDate() = this.toLocalDateTime(timeZone = TimeZone.UTC).date

fun LocalDate.toStringFormat() = "${this.dayOfMonth}/${this.monthNumber}/${this.year}"