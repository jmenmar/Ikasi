package com.jmenmar.ikasi.presentation.onboarding.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.data.badgesData
import com.jmenmar.ikasi.presentation.screens.badges.components.BadgeIcon
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.onboarding_badges_description
import ikasi.composeapp.generated.resources.onboarding_badges_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun BadgesPage(
    hasSeen: Boolean = false,
    onboardingSeen: () -> Unit = {}
) {
    OnboardingPageView(
        title = stringResource(Res.string.onboarding_badges_title),
        description = stringResource(Res.string.onboarding_badges_description),
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                BadgeIcon(badgesData[0].copy(completed = true))
                BadgeIcon(badgesData[20].copy(completed = true))
                BadgeIcon(badgesData[26].copy(completed = true))
                BadgeIcon(badgesData[23].copy(completed = true))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                BadgeIcon(badgesData[26].copy(completed = true))
                BadgeIcon(badgesData[17].copy(completed = true))
                BadgeIcon(badgesData[14].copy(completed = true))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                BadgeIcon(badgesData[5].copy(completed = true))
                BadgeIcon(badgesData[20].copy(completed = true))
                BadgeIcon(badgesData[23].copy(completed = true))
                BadgeIcon(badgesData[29].copy(completed = true))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                BadgeIcon(badgesData[17].copy(completed = true))
                BadgeIcon(badgesData[29].copy(completed = true))
                BadgeIcon(badgesData[11].copy(completed = true))
            }
        }
        LaunchedEffect(hasSeen) {
            if (hasSeen){
                onboardingSeen()
            }
        }
    }
}