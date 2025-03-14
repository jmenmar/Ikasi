package com.jmenmar.ikasi.presentation.screens.today

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.presentation.screens.today.components.AddActivitySheet
import com.jmenmar.ikasi.presentation.screens.today.components.TodayActivityRow
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun TodayScreen(
    innerPadding: PaddingValues,
    viewModel: TodayViewModel = koinViewModel<TodayViewModel>(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TodayView(
        innerPadding = innerPadding,
        todayActivities = state.todayActivities,
        isAddActivitySheetVisible = state.addActivitySheetVisible,
        selectedActivityType = state.selectedActivityType,
        updateActivity = { type, time ->
            viewModel.updateActivity(type, time)
            viewModel.changeAddActivitySheetVisibility(false)
        },
        deleteActivity = {
            viewModel.deleteActivity(it)
            viewModel.changeAddActivitySheetVisibility(false)
        },
        onChangeAddActivitySheetVisibility = {
            viewModel.changeAddActivitySheetVisibility(it)
        },
        onSelectActivityType = {
            viewModel.selectActivityType(it)
        },
    )
}

@Composable
fun TodayView(
    innerPadding: PaddingValues,
    todayActivities: List<Activity>,
    isAddActivitySheetVisible: Boolean,
    selectedActivityType: ActivityType,
    updateActivity: (ActivityType, ActivityTime) -> Unit,
    deleteActivity: (Activity) -> Unit,
    onChangeAddActivitySheetVisibility: (Boolean) -> Unit,
    onSelectActivityType: (ActivityType) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(top = 12.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Column(
                modifier = Modifier.align(Alignment.CenterStart),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                ActivityType.entries.sortedBy { it.priority }.forEach { type ->
                    TodayActivityRow(
                        type = type,
                        time = todayActivities.firstOrNull { it.type == type }?.time,
                        onClick = {
                            onSelectActivityType(type)
                            onChangeAddActivitySheetVisibility(true)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
            }
        }

        AddActivitySheet(
            isVisible = isAddActivitySheetVisible,
            activity = todayActivities.firstOrNull { it.type == selectedActivityType },
            activityType = selectedActivityType,
            changeVisibility = onChangeAddActivitySheetVisibility,
            updateActivity = updateActivity,
            deleteActivity = deleteActivity
        )
    }
}