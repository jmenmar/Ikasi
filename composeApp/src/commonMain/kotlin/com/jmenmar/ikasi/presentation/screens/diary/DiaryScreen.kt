package com.jmenmar.ikasi.presentation.screens.diary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.domain.model.Word
import com.jmenmar.ikasi.presentation.navigation.MoreRoute
import com.jmenmar.ikasi.presentation.screens.diary.components.ActivityOverview
import com.jmenmar.ikasi.presentation.screens.diary.components.DiaryExperienceView
import com.jmenmar.ikasi.presentation.screens.diary.components.DiaryHeader
import com.jmenmar.ikasi.presentation.screens.diary.components.DiaryMoreActivities
import com.jmenmar.ikasi.presentation.screens.diary.components.RecentActivityCard
import com.jmenmar.ikasi.presentation.utils.ActivityPeriod
import com.jmenmar.ikasi.presentation.utils.LevelProgress
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun DiaryScreen(
    navController: NavHostController,
    innerPadding: PaddingValues,
    viewModel: DiaryViewModel = koinViewModel<DiaryViewModel>(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    DiaryView(
        innerPadding = innerPadding,
        totalDays = state.totalDays,
        period = state.period,
        maxValue = state.maxValue,
        streak = state.streak,
        totalXp = state.totalXp,
        activities = state.filteredActivities,
        groupedActivities = state.groupedActivities,
        randomWords = state.randomWords,
        onPeriodChange = {
            viewModel.changeActivityPeriod(it)
        },
        onRandomizeWords = {
            viewModel.randomizeWords()
        },
        onNavigateToSettings = {
            navController.navigate(MoreRoute.Settings.route)
        },
        onNavigateToFlashcards = {
            navController.navigate(MoreRoute.Flashcards.route)
        }
    )
}

@Composable
fun DiaryView(
    innerPadding: PaddingValues,
    totalDays: Int,
    period: ActivityPeriod,
    maxValue: Int,
    streak: Int,
    totalXp: LevelProgress?,
    activities: List<Activity>,
    groupedActivities: Map<ActivityType, Int>,
    randomWords: List<Word> = emptyList(),
    onPeriodChange: (ActivityPeriod) -> Unit = {},
    onRandomizeWords: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onNavigateToFlashcards: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 12.dp)
            .padding(top = 12.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DiaryHeader(
            days = totalDays,
            onNavigateToSettings = onNavigateToSettings
        )
        if (groupedActivities.isNotEmpty() && totalDays > 0) {
            ActivityOverview(
                totalDays = totalDays,
                activities = groupedActivities
            )
        } else {
            LoadingActivityChart()
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (totalXp != null) {
            DiaryExperienceView(
                totalXp = totalXp,
                streak = streak
            )
        }
        DiaryMoreActivities(
            randomWords = randomWords,
            randomizeWords = onRandomizeWords,
            onNavigateToFlashcards = onNavigateToFlashcards
        )
        RecentActivityCard(
            period = period,
            maxValue = maxValue,
            activities = activities,
            onPeriodChange = onPeriodChange
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun LoadingActivityChart() {
    Box(
        modifier = Modifier
            .height(height = 280.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}