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
    }

    private fun getSettings() {
        viewModelScope.launch {
            ikasiRepository.getSettings().collect { settings ->
                _state.value = _state.value.copy(
                    settings = settings
                )
            }
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