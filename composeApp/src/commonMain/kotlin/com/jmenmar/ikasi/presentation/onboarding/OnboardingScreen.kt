package com.jmenmar.ikasi.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jmenmar.ikasi.presentation.navigation.NavigationRoute
import com.jmenmar.ikasi.presentation.onboarding.components.OnboardingPager
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = koinViewModel<OnboardingViewModel>(),
    navController: NavHostController,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state.onboarding) {
        if (state.onboarding != null && !state.onboarding!!) {
            navController.navigate(NavigationRoute.Main.route) {
                popUpTo(NavigationRoute.Onboarding.route) {
                    inclusive = true
                }
            }
        }
    }

    OnboardingView(
        isLevelOne = state.isLevelOne,
        onUpToLevelOne = { viewModel.upToLevelOne() },
        onFinish = {
            if (state.isLevelOne) {
                viewModel.finishOnboarding()
            }
        }
    )
}

@Composable
fun OnboardingView(
    isLevelOne: Boolean,
    onUpToLevelOne: () -> Unit = {},
    onFinish: () -> Unit = {}
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            OnboardingPager(
                isLevelOne = isLevelOne,
                onUpToLevelOne = onUpToLevelOne,
                onFinish = onFinish
            )
        }
    }
}