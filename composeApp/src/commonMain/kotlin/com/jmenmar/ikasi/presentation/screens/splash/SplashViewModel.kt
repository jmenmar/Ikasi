package com.jmenmar.ikasi.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val ikasiRepository: IkasiRepository
): ViewModel() {
    private val _state = MutableStateFlow(SplashState())
    val state: StateFlow<SplashState> = _state

    init {
        getOnboarding()
        getTheme()
    }

    private fun getTheme() {
        viewModelScope.launch {
            ikasiRepository.getSettings().collect {
                _state.value = _state.value.copy(
                    darkTheme = it?.darkTheme ?: true,
                    isLoading = false
                )
            }
        }
    }
    private fun getOnboarding() {
        viewModelScope.launch {
            ikasiRepository.getSettings().collect {
                _state.value = _state.value.copy(
                    onboarding = it?.onboarding ?: true
                )
            }
        }
    }
}