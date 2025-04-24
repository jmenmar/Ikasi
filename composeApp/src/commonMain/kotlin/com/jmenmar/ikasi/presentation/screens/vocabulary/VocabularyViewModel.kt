package com.jmenmar.ikasi.presentation.screens.vocabulary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import com.jmenmar.ikasi.domain.model.Word
import com.jmenmar.ikasi.domain.usecase.CheckBadgesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VocabularyViewModel(
    private val ikasiRepository: IkasiRepository,
    private val checkBadgesUseCase: CheckBadgesUseCase
): ViewModel() {
    private val _state = MutableStateFlow(VocabularyState())
    val state: StateFlow<VocabularyState> = _state

    init {
        getAllCards()
    }

    private fun getAllCards() {
        viewModelScope.launch {
            ikasiRepository.getWordsAsFlow().collect {
                updateState(allVocabulary = it)
            }
        }
    }

    private fun updateState(allVocabulary: List<Word>) {
        _state.value = _state.value.copy(
            allVocabulary = allVocabulary,
            isLoading = false
        )
        searchCards()
    }

    fun onNewWordTitleChange(newTitle: String) {
        _state.value = _state.value.copy(word = newTitle)
        searchCards()
    }

    fun onNewWordMeaningChange(newMeaning: String) {
        _state.value = _state.value.copy(meaning = newMeaning)
    }

    fun onNewWordNotesChange(newNotes: String) {
        _state.value = _state.value.copy(notes = newNotes)
    }

    fun onAddNewWord() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            ikasiRepository.newWord(
                Word(
                    title = _state.value.word,
                    meaning = _state.value.meaning,
                    notes = _state.value.notes,
                )
            ).getOrThrow()
            clearForm()
            checkBadgesUseCase()
        }
    }

    fun onDeleteWord(word: Word) {
        viewModelScope.launch {
            ikasiRepository.deleteWord(word).getOrThrow()
            _state.value = _state.value.copy(word = "")
            updateState(state.value.allVocabulary)
        }
    }

    fun clearForm() {
        _state.value = _state.value.copy(
            isLoading = false,
            word = "",
            meaning = "",
            notes = null,
            forceAddView = false,
        )
        searchCards()
    }

    fun onShowAddWordView() {
        _state.value = _state.value.copy(forceAddView = true)
    }

    private fun searchCards() {
        val search = state.value.word
        if (search.isNotEmpty()) {
            _state.value = _state.value.copy(
                forceAddView = false,
                filteredVocabulary = state.value.allVocabulary
                    .filter { it.title.contains(search, ignoreCase = true) },
            )
        } else {
            _state.value = _state.value.copy(
                forceAddView = false,
                filteredVocabulary = state.value.allVocabulary
            )
        }
    }
}