package com.jmenmar.ikasi.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.ikasi.domain.model.Badge
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import com.jmenmar.ikasi.presentation.utils.todayLocalDate
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val ikasiRepository: IkasiRepository
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    init {
        getNewBadgesCompleted()
    }

    private fun getNewBadgesCompleted() {
        viewModelScope.launch {
            ikasiRepository.getNewCompletedBadges().collectLatest { newBadgesCompleted ->
                _state.value = _state.value.copy(
                    newBadgesCompleted = newBadgesCompleted,
                )
                showNotification(newBadgesCompleted)
            }
        }
    }

    fun saveNewBadgesCompleted(badge: Badge) {
        viewModelScope.launch {
            ikasiRepository.updateBadge(badge =
                badge.copy(date = todayLocalDate().toEpochDays())
            )
        }
    }

    private fun showNotification(badges: List<Badge>) {
        badges.forEach { badge ->
            if (!_state.value.notifications.contains(badge)) {
                viewModelScope.launch {
                    delay(500)
                    _state.value = _state.value.copy(
                        notifications = _state.value.notifications + badge
                    )
                    delay(3000)
                    _state.value = _state.value.copy(
                        notifications = _state.value.notifications - badge
                    )
                    delay(500)
                    ikasiRepository.updateBadge(badge =
                        badge.copy(date = todayLocalDate().toEpochDays())
                    )
                }
            }
        }
    }
}