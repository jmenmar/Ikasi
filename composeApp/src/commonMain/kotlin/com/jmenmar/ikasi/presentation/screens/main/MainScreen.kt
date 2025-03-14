package com.jmenmar.ikasi.presentation.screens.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jmenmar.ikasi.presentation.navigation.BottomNavBar
import com.jmenmar.ikasi.presentation.navigation.NavigationGraph

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavigationGraph(
            navController = navController,
            innerPadding = innerPadding,
        )
    }
}