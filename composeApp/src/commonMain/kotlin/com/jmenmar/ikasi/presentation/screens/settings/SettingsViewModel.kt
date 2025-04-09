package com.jmenmar.ikasi.presentation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import com.jmenmar.ikasi.domain.usecase.DeleteAllUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val ikasiRepository: IkasiRepository,
    private val deleteAllUseCase: DeleteAllUseCase
): ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state

    init {
        getSettings()
        getTotalTime()
    }

    private fun getSettings() {
        viewModelScope.launch {
            ikasiRepository.getSettingsAsFlow().collect { settings ->
                _state.value = _state.value.copy(
                    settings = settings
                )
            }
        }
    }

    private fun getTotalTime() {
        viewModelScope.launch {
            val time = ikasiRepository.getActivities().sumOf { it.time }
            _state.value = _state.value.copy(
                totalTime = time
            )
        }
    }

    fun showConfirmResetDialog(isVisible: Boolean) {
        _state.value = _state.value.copy(
            isConfirmDialogVisible = isVisible
        )
    }

    fun checkDeleteVocabulary(isDeletingVocabulary: Boolean) {
        _state.value = _state.value.copy(
            isDeletingVocabulary = isDeletingVocabulary
        )
    }

    fun switchTheme(isDarkTheme: Boolean, onThemeChange: (Boolean) -> Unit) {
        if (_state.value.settings != null) {
            viewModelScope.launch {
                ikasiRepository.newSettings(
                    settings = _state.value.settings!!.copy(
                        darkTheme = isDarkTheme
                    )
                ).onSuccess {
                    onThemeChange(isDarkTheme)
                }
            }
        }
    }

    fun resetProgress() {
        viewModelScope.launch {
            deleteAllUseCase(
                deleteVocabulary = _state.value.isDeletingVocabulary
            ).onSuccess {
                _state.value = _state.value.copy(
                    isNavigating = true
                )
            }
        }
    }
}