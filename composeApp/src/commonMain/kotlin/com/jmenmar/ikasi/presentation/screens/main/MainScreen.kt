package com.jmenmar.ikasi.presentation.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jmenmar.ikasi.presentation.navigation.BottomNavBar
import com.jmenmar.ikasi.presentation.navigation.NavigationGraph
import com.jmenmar.ikasi.presentation.screens.main.components.BadgeNotificationView
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MainScreen(
    mainNavController: NavHostController,
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = koinViewModel<MainViewModel>(),
    onThemeChange: (Boolean) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.showNotification){
        delay(5000)
        viewModel.saveNewBadgesCompleted(badges = state.newBadgesCompleted)
    }

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            NavigationGraph(
                mainNavController = mainNavController,
                navController = navController,
                innerPadding = innerPadding,
                onThemeChange = onThemeChange
            )
            AnimatedVisibility(
                visible = state.showNotification,
                enter = slideInVertically (
                    initialOffsetY = { -it },
                    animationSpec = tween(durationMillis = 1000)
                ),
            ) {
                Column(
                    modifier = Modifier.padding(top = 40.dp)
                ) {
                    state.newBadgesCompleted.forEach {
                        BadgeNotificationView(badge = it)
                    }
                }
            }
        }
    }
}