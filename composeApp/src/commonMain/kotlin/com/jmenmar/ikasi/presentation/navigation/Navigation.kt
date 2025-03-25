package com.jmenmar.ikasi.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jmenmar.ikasi.presentation.onboarding.OnboardingScreen
import com.jmenmar.ikasi.presentation.screens.diary.DiaryScreen
import com.jmenmar.ikasi.presentation.screens.flashcards.FlashcardsScreen
import com.jmenmar.ikasi.presentation.screens.main.MainScreen
import com.jmenmar.ikasi.presentation.screens.settings.SettingsScreen
import com.jmenmar.ikasi.presentation.screens.splash.SplashScreen
import com.jmenmar.ikasi.presentation.screens.today.TodayScreen
import com.jmenmar.ikasi.presentation.screens.vocabulary.VocabularyScreen

@Composable
fun Navigation(
    mainNavController: NavHostController = rememberNavController(),
    onThemeChange: (Boolean) -> Unit,
) {
    NavHost(
        navController = mainNavController,
        startDestination = NavigationRoute.Splash.route
    ) {
        composable(NavigationRoute.Splash.route) {
            SplashScreen(
                navController = mainNavController,
                onThemeChange = onThemeChange
            )
        }

        composable(NavigationRoute.Onboarding.route) {
            OnboardingScreen(
                navController = mainNavController
            )
        }
        composable(NavigationRoute.Main.route) {
            MainScreen(
                mainNavController = mainNavController,
                onThemeChange = onThemeChange
            )
        }
    }
}

@Composable
fun NavigationGraph(
    mainNavController: NavHostController,
    navController: NavHostController,
    innerPadding: PaddingValues,
    onThemeChange: (Boolean) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavRoute.Today.route
    ) {
        composable(route = BottomNavRoute.Diary.route) {
            DiaryScreen(
                navController = navController,
                innerPadding = innerPadding,
            )
        }
        composable(route = BottomNavRoute.Today.route) {
            TodayScreen(
                innerPadding = innerPadding,
            )
        }
        composable(route = BottomNavRoute.Vocabulary.route) {
            VocabularyScreen(
                innerPadding = innerPadding,
            )
        }
        composable(route = MoreRoute.Flashcards.route) {
            FlashcardsScreen(
                navController = navController
            )
        }
        composable(route = MoreRoute.Settings.route) {
            SettingsScreen(
                mainNavController = mainNavController,
                navController = navController,
                onThemeChange = onThemeChange,
            )
        }
    }
}