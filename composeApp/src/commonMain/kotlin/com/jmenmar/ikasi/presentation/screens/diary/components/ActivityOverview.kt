package com.jmenmar.ikasi.presentation.screens.diary.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.presentation.components.RadarChart
import org.jetbrains.compose.resources.stringResource

@Composable
fun ActivityOverview(
    totalDays: Int,
    activities: Map<ActivityType, Int>,
) {
    val max = when {
        activities.isEmpty() -> 1
        (activities.values.max() > totalDays * 60) -> activities.values.max()
        else -> totalDays * 60
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val labels = activities.keys.toList().map { stringResource(it.skill) }
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
                    activities.values.forEach {
                        add((it*1f)/max)
                    }
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