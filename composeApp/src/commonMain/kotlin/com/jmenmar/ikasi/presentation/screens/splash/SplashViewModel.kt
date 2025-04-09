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
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            ikasiRepository.getSettingsAsFlow().collect {
                _state.value = _state.value.copy(
                    onboarding = it?.onboarding ?: true,
                    darkTheme = it?.darkTheme ?: true,
                    isLoading = false
                )
            }
        }
    }
}