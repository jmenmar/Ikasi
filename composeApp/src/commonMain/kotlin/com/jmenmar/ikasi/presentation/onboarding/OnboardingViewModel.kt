package com.jmenmar.ikasi.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.ikasi.domain.model.Settings
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import com.jmenmar.ikasi.presentation.utils.todayLocalDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val ikasiRepository: IkasiRepository
): ViewModel() {
    private val _state = MutableStateFlow(OnboardingState())
    val state: StateFlow<OnboardingState> = _state


    init {
        getOnboardingState()
    }

    private fun getOnboardingState() {
        viewModelScope.launch {
            ikasiRepository.getSettings().collect { settings ->
                _state.value = _state.value.copy(
                    onboarding = settings?.onboarding ?: true
                )
            }
        }
    }

    fun upToLevelOne() {
        _state.value = _state.value.copy(
            isLevelOne = true
        )
    }

    fun finishOnboarding() {
        viewModelScope.launch {
            ikasiRepository.newSettings(
                settings = Settings(
                    onboarding = false,
                    startDate = todayLocalDate(),
                    darkTheme = true
                )
            )
        }
    }
}