package com.jmenmar.ikasi.presentation.onboarding.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.presentation.components.RadarChart
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.onboarding_improve_description
import ikasi.composeapp.generated.resources.onboarding_improve_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun SkillsPage() {
    OnboardingPageView(
        title = stringResource(Res.string.onboarding_improve_title),
        description = stringResource(Res.string.onboarding_improve_description),
    ) {
        val labels = ActivityType.entries.map { stringResource(it.skill) }
        val pathEffects = with(LocalDensity.current) {
            remember(this) {
                listOf(
                    Stroke(width = 1.dp.toPx(), pathEffect = PathEffect.dashPathEffect(
                        floatArrayOf(1.dp.toPx(), 1.dp.toPx())
                    )),
                    Stroke(width = 1.dp.toPx()),
                ).toList()
            }
        }
        val data = remember {
            buildList {
                add(buildList {
                    add(0.85f)
                    add(0.5f)
                    add(0.6f)
                    add(0.9f)
                    add(0.6f)
                }.toList())
            }.toList()
        }

        RadarChart(
            modifier = Modifier
                .width(350.dp)
                .height(280.dp),
            values = data,
            labels = labels,
            drawStyles = pathEffects,
            labelStyle = TextStyle(textAlign = TextAlign.Center)
        )
    }
}