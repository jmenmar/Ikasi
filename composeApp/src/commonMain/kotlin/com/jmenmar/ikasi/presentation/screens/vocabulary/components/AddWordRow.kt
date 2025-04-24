package com.jmenmar.ikasi.presentation.screens.vocabulary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddWordRow(
    isVisible: Boolean,
    word: String,
    onShowAddWordView: () -> Unit = {}
) {
    if (!isVisible) return
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp)
                .clickable { onShowAddWordView() },
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = word,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Add,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        }
        HorizontalDivider()
    }
}