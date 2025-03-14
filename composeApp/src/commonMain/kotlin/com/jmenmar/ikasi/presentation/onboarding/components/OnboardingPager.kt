package com.jmenmar.ikasi.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.presentation.onboarding.GoalPeriod

@Composable
fun OnboardingPager(
    modifier: Modifier = Modifier,
    selectedPeriod: GoalPeriod?,
    onSelectGoalPeriod: (GoalPeriod) -> Unit = {},
    onFinish: () -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { 2 })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.weight(0.75f),
            contentAlignment = Alignment.Center
        ) {
            HorizontalPager(
                verticalAlignment = Alignment.Top,
                pageSpacing = 14.dp,
                beyondViewportPageCount = 3,
                state = pagerState
            ) { page ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when(page) {
                        0 ->
                            WelcomePage()
                        1 ->
                            GoalPeriodPage(
                                selectedPeriod = selectedPeriod,
                                onSelectPeriod = onSelectGoalPeriod,
                            )
                    }
                }
            }
        }
        Row(
            modifier = Modifier.weight(0.1f),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surface
                }
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                )
            }
        }
        Box(modifier = Modifier.weight(0.15f)) {
            Button(
                enabled = selectedPeriod != null,
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(start = 12.dp, end = 14.dp),
                onClick = onFinish,
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = "Start"
                )
            }
        }
    }
}