package com.jmenmar.ikasi.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RadarChart(
    labels: List<String>,
    values: List<List<Float>>,
    color: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
    drawStyles: List<DrawStyle>,
    modifier: Modifier = Modifier,
    padding: Dp = 10.dp,
    lineCount: Int = 4,
    lineColor: Color = Color.LightGray,
    durationMillis: Int = 700,
    labelStyle: TextStyle = TextStyle.Default,
) {
    val labelsColor = MaterialTheme.colorScheme.onBackground

    val itemSize = 5
    check(labels.size == itemSize && values.all { it.size == itemSize } && drawStyles.isNotEmpty())

    val animationValues = remember(values.size, itemSize) {
        buildList {
            repeat(values.size) {
                add(buildList {
                    repeat(itemSize) {
                        add(Animatable(0f))
                    }
                })
            }
        }
    }
    LaunchedEffect(values) {
        values.forEachIndexed { i, value ->
            value.forEachIndexed { j, fl ->
                launch {
                    animationValues[i][j].animateTo(fl, animationSpec = tween(durationMillis = durationMillis))
                }
            }
        }
    }
    // Calculate the radian unit for a pentagon (5 sides)
    val radianUnit = remember(itemSize) {
        2 * PI / itemSize
    }
    val chartPath = remember {
        Path()
    }
    val dataPath = remember {
        Path()
    }
    val textMeasurer = rememberTextMeasurer()
    Canvas(modifier = modifier) {
        // Draw Chart Path
        chartPath.reset()
        val measuredTexts = labels.map { textMeasurer.measure(text = AnnotatedString(it.replace(oldValue = " ", newValue = "\n")), style = labelStyle) }
        val maxRadius = (0 until itemSize).mapIndexed { index, i ->
            size.maxLengthFromCenter(i * radianUnit - PI / 2) - padding.toPx() - measuredTexts[index].size.toSize()
                .maxLengthFromCenter(i * radianUnit - PI / 2) * 2
        }.min()
        val gap = maxRadius / lineCount
        repeat(lineCount) { step ->
            val radius = (step + 1) * gap
            (0..itemSize).forEach {
                val x = radius * cos(it * radianUnit - PI / 2)
                val y = radius * sin(it * radianUnit - PI / 2)
                if (it == 0) chartPath.moveTo(x.toFloat(), y.toFloat())
                else chartPath.lineTo(x.toFloat(), y.toFloat())
            }
        }
        // Draw center-to-vertex lines
        (0 until itemSize).forEach {
            val x = maxRadius * cos(it * radianUnit - PI / 2)
            val y = maxRadius * sin(it * radianUnit - PI / 2)
            chartPath.moveTo(0f, 0f) // Center of the chart
            chartPath.lineTo(x.toFloat(), y.toFloat()) // Vertex
        }
        translate(left = center.x, top = center.y) {
            drawPath(path = chartPath, color = lineColor, style = Stroke(width = 1.dp.toPx()))
        }
        // Generate Data Path
        animationValues.forEachIndexed { i, value ->
            dataPath.reset()
            value.forEachIndexed { j, animatable ->
                val radius = maxRadius * animatable.value
                val x = radius * cos(j * radianUnit - PI / 2)
                val y = radius * sin(j * radianUnit - PI / 2)
                if (j == 0) dataPath.moveTo(x.toFloat(), y.toFloat())
                else dataPath.lineTo(x.toFloat(), y.toFloat())
            }
            dataPath.close()
            translate(left = center.x, top = center.y) {
                val pathEffect = drawStyles[i % drawStyles.size]
                drawPath(path = dataPath, color = color, style = Fill)
                drawPath(path = dataPath, color = color.copy(alpha = 1f), style = pathEffect)
            }
        }
        // Draw
        translate(left = center.x, top = center.y) {
            measuredTexts.forEachIndexed { index, label ->
                val radian = index * radianUnit - PI / 2
                val radius =
                    size.maxLengthFromCenter(radian) - label.size.toSize().maxLengthFromCenter(radian)
                val x = radius * cos(radian) - label.size.width / 2
                val y = radius * sin(radian) - label.size.height / 2
                drawText(
                    textLayoutResult = label,
                    topLeft = Offset(x.toFloat(), y.toFloat()),
                    color = labelsColor
                )
            }
        }
    }
}

private fun Size.maxLengthFromCenter(radian: Double): Double {
    val width: Double = (1.0 / cos(radian)).let {
        if (it.isFinite()) {
            it * width / 2.0
        } else {
            width / 2.0
        }
    }
    val height: Double = (1.0 / sin(radian)).let {
        if (it.isFinite()) {
            it * height / 2.0
        } else {
            height / 2.0
        }
    }
    return minOf(abs(width), abs(height))
}