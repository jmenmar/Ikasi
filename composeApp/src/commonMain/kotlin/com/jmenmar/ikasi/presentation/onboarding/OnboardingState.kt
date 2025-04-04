package com.jmenmar.ikasi.presentation.onboarding

data class OnboardingState(
    val isOnboardingSeen: Boolean = false,
    val onboarding: Boolean? = null,
    val currentOnboardingPage: Int = 0,
)
