package com.jmenmar.ikasi.presentation.screens.badges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BadgesViewModel(
    private val ikasiRepository: IkasiRepository
) : ViewModel() {
    private val _state = MutableStateFlow(BadgesState())
    val state: StateFlow<BadgesState> = _state

    init {
        getBadges()
    }

    fun toggleStyle() {
        _state.value = _state.value.copy(
            isListView = !_state.value.isListView
        )
    }

    private fun getBadges() {
        viewModelScope.launch {
            ikasiRepository.getAllBadges().collect { badges ->
                _state.value = _state.value.copy(
                    badges = badges
                )
            }
        }
    }
}