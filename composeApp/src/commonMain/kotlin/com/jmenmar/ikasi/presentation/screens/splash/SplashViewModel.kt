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
    private val _state = MutableStateFlow<Boolean?>(null)
    val state: StateFlow<Boolean?> = _state

    init {
        getOnboarding()
    }

    private fun getOnboarding() {
        viewModelScope.launch {
            ikasiRepository.getSettings().collect {
                _state.value = it?.onboarding ?: true
            }
        }
    }
}