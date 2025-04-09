package com.jmenmar.ikasi.presentation.screens.vocabulary.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Word
import com.jmenmar.ikasi.ui.Red
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.delete
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@Composable
fun WordCard(
    word: Word,
    onDeleteClick: (Word) -> Unit
) {
    var isShown by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val swipeState = rememberSwipeToDismissBoxState()
    when (swipeState.currentValue) {
        SwipeToDismissBoxValue.EndToStart -> {}

        SwipeToDismissBoxValue.StartToEnd -> {}

        SwipeToDismissBoxValue.Settled -> {}
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .background(MaterialTheme.colorScheme.background)
            .animateContentSize()
            .clickable {
                if (swipeState.currentValue != SwipeToDismissBoxValue.EndToStart) {
                    isShown = !isShown
                }
            },
    ) {
        SwipeToDismissBox(
            state = swipeState,
            modifier = Modifier.padding(vertical = 1.dp),
            enableDismissFromStartToEnd = false,
            backgroundContent = {
                Box(
                    Modifier
                        .fillMaxHeight()
                        ,
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .clickable {
                                    coroutineScope.launch {
                                        swipeState.reset()
                                    }
                                },
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .background(Red.copy(alpha = 0.2f)),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                modifier = Modifier.clickable(
                                    onClick = {
                                        if (swipeState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                                            onDeleteClick(word)
                                        } else {
                                            isShown = !isShown
                                        }
                                    },
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                ),
                                text = stringResource(Res.string.delete),
                                color = Red
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Icon(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .clickable(
                                        onClick = {
                                            if (swipeState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                                                onDeleteClick(word)
                                            } else {
                                                isShown = !isShown
                                            }
                                        },
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                    ),
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Red,
                            )
                        }
                    }

                }
            },
        ) {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = word.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    if (isShown) {
                        Text(
                            text = word.meaning,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        if (!word.notes.isNullOrEmpty()) {
                            Text(
                                text = word.notes,
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    }
                }
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = if (isShown) Icons.Default.KeyboardArrowUp
                    else Icons.Default.KeyboardArrowDown,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            }
        }
        HorizontalDivider()
    }
}