package com.jmenmar.ikasi.presentation.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jmenmar.ikasi.presentation.navigation.NavigationRoute
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = koinViewModel<SplashViewModel>(),
    onThemeChange: (Boolean) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state.darkTheme) {
        if (state.darkTheme != null) {
            onThemeChange(state.darkTheme!!)
        }
    }
    LaunchedEffect(key1 = state) {
        if (state.onboarding != null && state.darkTheme != null && !state.isLoading) {
            if (state.onboarding!!) {
                navController.navigate(NavigationRoute.Onboarding.route){
                    popUpTo(NavigationRoute.Splash.route) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(NavigationRoute.Main.route){
                    popUpTo(NavigationRoute.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    if (state.isLoading) {
        SplashView()
    }
}

@Composable
fun SplashView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}