package com.v2ray.ang.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.v2ray.ang.R
import com.v2ray.ang.compose.*
import com.v2ray.ang.util.Utils

/**
 * PLUTO 2027 — Bottom Status Card with quick actions
 * Modern, bouncy, with pull-up gestures
 */
@Composable
fun MainBottomBar(
    displayText: String,
    isRunning: Boolean,
    isDarkTheme: Boolean,
    onAction: (MainAction) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Quick actions row (shown when expanded)
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .surfaceGlass(RoundedCornerShape(20.dp), isDarkTheme)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                QuickAction(
                    icon = R.drawable.ic_speed_24dp,
                    label = "Speed",
                    onClick = { onAction(MainAction.TestAllServers); expanded = false }
                )
                QuickAction(
                    icon = R.drawable.ic_reorder_24dp,
                    label = "Sort",
                    onClick = { onAction(MainAction.SortServers); expanded = false }
                )
                QuickAction(
                    icon = R.drawable.ic_add_24dp,
                    label = "Import",
                    onClick = { onAction(MainAction.ImportConfig); expanded = false }
                )
            }
        }

        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
            // Main status card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .glassPanel(shape = RoundedCornerShape(24.dp), isDarkTheme = isDarkTheme, elevation = 4.dp)
                    .clickable { expanded = !expanded }
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Animated status dot
                    Box(
                        Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(
                                if (isRunning) glassSuccess
                                else if (isDarkTheme) Color(0xFF4B5563) else Color(0xFFD1D5DB)
                            )
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = displayText,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp,
                            color = if (isDarkTheme) glassTextDark else glassTextLight
                        )
                    )
                }

                // FAB Start/Stop
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    if (isRunning) glassSuccess else glassAccent,
                                    if (isRunning) Color(0xFF10B981) else glassAccentDim
                                )
                            )
                        )
                        .clickable { onAction(MainAction.ToggleService) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = if (isRunning) painterResource(R.drawable.ic_stop_24dp)
                        else painterResource(R.drawable.ic_play_24dp),
                        contentDescription = if (isRunning) "Stop" else "Start",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun QuickAction(
    icon: Int,
    label: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(brush = Brush.linearGradient(listOf(glassAccent.copy(alpha = 0.15f), glassAccentDim.copy(alpha = 0.10f)))),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = label,
                tint = glassAccent,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(label, style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp), color = glassAccent)
    }
}