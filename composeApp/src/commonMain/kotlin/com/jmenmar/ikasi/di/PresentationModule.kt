package com.jmenmar.ikasi.di

import com.jmenmar.ikasi.data.repository.IkasiRepositoryImpl
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import com.jmenmar.ikasi.domain.usecase.DeleteAllUseCase
import com.jmenmar.ikasi.presentation.onboarding.OnboardingViewModel
import com.jmenmar.ikasi.presentation.screens.diary.DiaryViewModel
import com.jmenmar.ikasi.presentation.screens.flashcards.FlashcardsViewModel
import com.jmenmar.ikasi.presentation.screens.settings.SettingsViewModel
import com.jmenmar.ikasi.presentation.screens.splash.SplashViewModel
import com.jmenmar.ikasi.presentation.screens.today.TodayViewModel
import com.jmenmar.ikasi.presentation.screens.vocabulary.VocabularyViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val presentationModule = module {
    factoryOf(::IkasiRepositoryImpl) bind IkasiRepository::class
    factoryOf(::DeleteAllUseCase)
    viewModelOf(::TodayViewModel)
    viewModelOf(::DiaryViewModel)
    viewModelOf(::VocabularyViewModel)
    viewModelOf(::FlashcardsViewModel)
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::SettingsViewModel)
}