package com.jmenmar.ikasi.presentation.screens.flashcards.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.ui.GreyMedium
import com.jmenmar.ikasi.ui.White
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.correct
import ikasi.composeapp.generated.resources.incorrect
import ikasi.composeapp.generated.resources.result_x
import org.jetbrains.compose.resources.stringResource

@Composable
fun FlashcardsFinishView(
    correct: Int = 0,
    incorrect: Int = 0,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn {
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .padding(bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 48.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "$correct",
                                    style = MaterialTheme.typography.displayLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = stringResource(Res.string.correct),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "$incorrect",
                                    style = MaterialTheme.typography.displayLarge,
                                    color = GreyMedium
                                )
                                Text(
                                    text = stringResource(Res.string.incorrect),
                                    color = GreyMedium
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(vertical = 8.dp),
                    text = stringResource(
                        Res.string.result_x,
                        (correct.toFloat() / (correct + incorrect) * 100).toInt()
                    ),
                    style = MaterialTheme.typography.headlineSmall,
                    color = White,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}