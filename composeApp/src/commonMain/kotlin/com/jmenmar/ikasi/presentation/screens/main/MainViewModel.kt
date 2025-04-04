package com.jmenmar.ikasi.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.ikasi.domain.model.Badge
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import com.jmenmar.ikasi.presentation.utils.todayLocalDate
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
                    showNotification = newBadgesCompleted.isNotEmpty()
                )
            }
        }
    }

    fun saveNewBadgesCompleted(badges: List<Badge>) {
        viewModelScope.launch {
            ikasiRepository.updateBadges(badges = badges.map {
                it.copy(date = todayLocalDate().toEpochDays())
            })
            _state.value = _state.value.copy(
                showNotification = false
            )
        }
    }
}