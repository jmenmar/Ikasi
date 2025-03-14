package com.jmenmar.ikasi.presentation.screens.today

enum class ActivityTime(val time: Int, val label: String) {
    HALF_HOUR(time = 30, label = "30 min"),
    ONE_HOUR(time = 60, label = "1 hr"),
    HALF_AND_HOUR(time = 90, label = "1:30 hr"),
    TWO_HOURS(time = 120, label = "2+ hrs"),
}