package com.jmenmar.ikasi.presentation.screens.today.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.presentation.screens.today.ActivityTime
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.add
import ikasi.composeapp.generated.resources.update
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddActivitySheet(
    isVisible: Boolean,
    activity: Activity? = null,
    activityType: ActivityType,
    changeVisibility: (Boolean) -> Unit,
    updateActivity: (ActivityType, ActivityTime) -> Unit,
    deleteActivity: (Activity) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    var time: ActivityTime? by remember { mutableStateOf(null) }
    time = if (activity != null) {
        ActivityTime.entries.firstOrNull { it.time == activity.time }
    } else {
        null
    }

    if (isVisible) {
        ModalBottomSheet(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            onDismissRequest = { changeVisibility(false) },
            sheetState = sheetState,
            dragHandle = null
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(color = activityType.color)
                    ) {
                        Icon(
                            modifier = Modifier.padding(12.dp),
                            painter = painterResource(activityType.icon),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = stringResource(activityType.title),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Box(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .clip(RoundedCornerShape(6.dp))
                                .background(color = MaterialTheme.colorScheme.surface),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 6.dp,
                                    vertical = 2.dp
                                ),
                                text = stringResource(activityType.skill),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            )
                        }
                    }
                    if (activity != null) {
                        IconButton(
                            onClick = { deleteActivity(activity) }
                        ) {
                            Icon(
                                modifier = Modifier.clip(RoundedCornerShape(12.dp)),
                                imageVector = Icons.Default.Delete,
                                contentDescription = ""
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.size(24.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ActivityTime.entries.forEach { activityTime ->
                        ActivityTimeChip(
                            modifier = Modifier.weight(1f),
                            selectionColor = activityType.color,
                            activityPeriod = activityTime,
                            selected = activityTime == time,
                            onPeriodChange = { time = it }
                        )
                    }
                }
                Spacer(modifier = Modifier.size(24.dp))
                Button(
                    enabled = (activity == null && time != null) ||
                            (activity != null && time != ActivityTime.entries.first { it.time == activity.time }),
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        time?.let { updateActivity(activityType, it) }
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = if (activity != null) Icons.Default.Edit
                            else Icons.Default.Add,
                            contentDescription = ""
                        )
                        Text(
                            text = if (activity != null) stringResource(Res.string.update)
                            else stringResource(Res.string.add),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ActivityTimeChip(
    modifier: Modifier = Modifier,
    selectionColor: Color,
    activityPeriod: ActivityTime,
    selected: Boolean = false,
    onPeriodChange: (ActivityTime) -> Unit = {},
) {
    FilterChip(
        modifier = modifier,
        onClick = { onPeriodChange(activityPeriod) },
        label = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = activityPeriod.label,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        selected = selected,
        colors = FilterChipDefaults.filterChipColors().copy(
            selectedContainerColor = selectionColor,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}