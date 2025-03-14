package com.jmenmar.ikasi.presentation.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jmenmar.ikasi.presentation.navigation.NavigationRoute
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = koinViewModel<SplashViewModel>()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state) {
        if (state != null) {
            if (state!!) {
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
}