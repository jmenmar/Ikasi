package com.jmenmar.ikasi.presentation.screens.vocabulary.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.presentation.components.BasicTextField
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.add
import ikasi.composeapp.generated.resources.meaning
import ikasi.composeapp.generated.resources.notes_optional
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddVocabularyView(
    focusManager: FocusManager,
    word: String,
    meaning: String,
    notes: String?,
    onMeaningChange: (String) -> Unit = {},
    onNotesChange: (String) -> Unit = {},
    onAddNewWord: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = word,
                style = MaterialTheme.typography.headlineSmall
            )
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 42.dp),
                value = meaning,
                placeholder = stringResource(Res.string.meaning),
                onValueChange = { onMeaningChange(it) },
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onAny = {
                    focusManager.moveFocus(FocusDirection.Next)
                }),
            )
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 42.dp),
                value = notes?: "",
                placeholder = stringResource(Res.string.notes_optional),
                onValueChange = { onNotesChange(it) },
                minLines = 5,
                maxLines = 5,
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Text,
                ),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                enabled = meaning.isNotEmpty() && word.isNotEmpty(),
                shape = RoundedCornerShape(12.dp),
                onClick = { onAddNewWord() }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = ""
                    )
                    Text(text = stringResource(Res.string.add))
                }
            }
        }
    }
}