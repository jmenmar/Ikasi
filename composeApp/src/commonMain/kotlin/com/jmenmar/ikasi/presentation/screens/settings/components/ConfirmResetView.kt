package com.jmenmar.ikasi.presentation.screens.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jmenmar.ikasi.ui.Pink
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.are_you_sure_reset_progress
import ikasi.composeapp.generated.resources.confirm
import ikasi.composeapp.generated.resources.delete_vocabulary
import org.jetbrains.compose.resources.stringResource

@Composable
fun ConfirmResetView(
    isVisible: Boolean,
    isDeletingVocabulary: Boolean,
    onCheckDeleteVocabulary: (Boolean) -> Unit,
    onClickConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    if (!isVisible) return
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier.padding(horizontal = 24.dp),
            colors = CardDefaults.cardColors().copy(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
            )
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.are_you_sure_reset_progress),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isDeletingVocabulary,
                        onCheckedChange = onCheckDeleteVocabulary
                    )
                    Text(
                        modifier = Modifier
                            .clickable { onCheckDeleteVocabulary(!isDeletingVocabulary) },
                        text = stringResource(Res.string.delete_vocabulary)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    shape = RoundedCornerShape(12.dp),
                    onClick = onClickConfirm,
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = Pink,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    )
                ) {
                    Text(text = stringResource(Res.string.confirm))
                }
            }
        }
    }
}