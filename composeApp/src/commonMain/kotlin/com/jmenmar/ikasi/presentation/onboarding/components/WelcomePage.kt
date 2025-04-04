package com.jmenmar.ikasi.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.ActivityType
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.onboarding_welcome_description
import ikasi.composeapp.generated.resources.onboarding_welcome_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun WelcomePage() {
    OnboardingPageView(
        title = stringResource(Res.string.onboarding_welcome_title),
        description = stringResource(Res.string.onboarding_welcome_description),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ActivityType.entries.sortedBy { it.priority }.forEach { type ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val padding = when (type) {
                        ActivityType.THEORY -> 0.dp
                        ActivityType.SPEAKING -> 25.dp
                        ActivityType.LISTENING -> 50.dp
                        ActivityType.READING -> 25.dp
                        ActivityType.WRITING -> 0.dp
                    }
                    Spacer(Modifier.padding(horizontal = padding))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(color = type.color)
                    ) {
                        Icon(
                            modifier = Modifier.padding(8.dp).size(30.dp),
                            painter = painterResource(type.icon),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                    Text(
                        text = stringResource(type.title),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}