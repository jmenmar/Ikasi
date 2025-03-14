package com.jmenmar.ikasi.presentation.onboarding

data class OnboardingState(
    val selectedPeriod: GoalPeriod? = null,
    val onboarding: Boolean? = null,
    val currentOnboardingPage: Int = 0,
)
