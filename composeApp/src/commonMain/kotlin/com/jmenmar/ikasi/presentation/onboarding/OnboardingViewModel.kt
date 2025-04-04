package com.jmenmar.ikasi.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import com.jmenmar.ikasi.domain.usecase.StartingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val ikasiRepository: IkasiRepository,
    private val startingUseCase: StartingUseCase
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

    fun onboardingSeen() {
        _state.value = _state.value.copy(
            isOnboardingSeen = true
        )
    }

    fun finishOnboarding() {
        viewModelScope.launch {
            startingUseCase()
        }
    }
}