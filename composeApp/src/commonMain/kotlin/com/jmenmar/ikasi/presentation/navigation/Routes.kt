package com.jmenmar.ikasi.presentation.navigation

import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.diary
import ikasi.composeapp.generated.resources.ic_bookmark
import ikasi.composeapp.generated.resources.ic_check_circle
import ikasi.composeapp.generated.resources.ic_note_stack
import ikasi.composeapp.generated.resources.today
import ikasi.composeapp.generated.resources.vocabulary
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class NavigationRoute(val route: String) {
    data object Onboarding : NavigationRoute("ONBOARDING")
    data object Main : NavigationRoute("MAIN")
    data object Splash : NavigationRoute("SPLASH")
}

sealed class BottomNavRoute(
    val route: String,
    val title: StringResource,
    val icon: DrawableResource
) {
    data object Today : BottomNavRoute(
        route = "TODAY",
        title = Res.string.today,
        icon = Res.drawable.ic_check_circle
    )

    data object Diary : BottomNavRoute(
        route = "DIARY",
        title = Res.string.diary,
        icon = Res.drawable.ic_bookmark
    )

    data object Vocabulary : BottomNavRoute(
        route = "VOCABULARY",
        title = Res.string.vocabulary,
        icon = Res.drawable.ic_note_stack
    )
}

sealed class MoreRoute(val route: String) {
    data object Flashcards : MoreRoute("FLASHCARDS")
    data object Settings : MoreRoute("SETTINGS")
}