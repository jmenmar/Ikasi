package com.jmenmar.ikasi.presentation.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jmenmar.ikasi.domain.model.Settings
import com.jmenmar.ikasi.presentation.navigation.NavigationRoute
import com.jmenmar.ikasi.presentation.screens.settings.components.ConfirmResetView
import com.jmenmar.ikasi.presentation.screens.settings.components.SettingsContent
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun SettingsScreen(
    mainNavController: NavHostController,
    navController: NavHostController,
    viewModel: SettingsViewModel = koinViewModel<SettingsViewModel>(),
    onThemeChange: (Boolean) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isNavigating) {
        if (state.isNavigating) {
            mainNavController.navigate(NavigationRoute.Splash.route) {
                popUpTo(NavigationRoute.Main.route) {
                    inclusive = true
                }
            }
        }
    }

    SettingsView(
        settings = state.settings,
        totalTime = state.totalTime,
        isConfirmDialogVisible = state.isConfirmDialogVisible,
        isDeletingVocabulary = state.isDeletingVocabulary,
        onThemeChange = { isDarkTheme ->
            viewModel.switchTheme(isDarkTheme) {
                onThemeChange(it)
            }
        },
        onCheckDeleteVocabulary = {
            viewModel.checkDeleteVocabulary(it)
        },
        onConfirmReset = {
            viewModel.showConfirmResetDialog(false)
            viewModel.resetProgress()
        },
        onResetClick = {
            viewModel.showConfirmResetDialog(true)
        },
        onDismissResetDialog = {
            viewModel.showConfirmResetDialog(false)
        },
        onNavigateBack = {
            navController.popBackStack()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsView(
    settings: Settings?,
    totalTime: Int,
    isConfirmDialogVisible: Boolean,
    isDeletingVocabulary: Boolean,
    onThemeChange: (Boolean) -> Unit = {},
    onResetClick: () -> Unit = {},
    onCheckDeleteVocabulary: (Boolean) -> Unit = {},
    onConfirmReset: () -> Unit = {},
    onDismissResetDialog: () -> Unit = {},
    onNavigateBack: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(Res.string.settings))
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (settings == null) {
                CircularProgressIndicator()
                return@Box
            }
            SettingsContent(
                modifier = Modifier.padding(innerPadding),
                isDarkTheme = settings.darkTheme,
                onThemeChange = onThemeChange,
                startDate = settings.startDate,
                totalTime = totalTime,
                onReset = onResetClick
            )
            ConfirmResetView(
                isVisible = isConfirmDialogVisible,
                isDeletingVocabulary = isDeletingVocabulary,
                onCheckDeleteVocabulary = onCheckDeleteVocabulary,
                onClickConfirm = onConfirmReset,
                onDismiss = onDismissResetDialog,
            )
        }
    }
}