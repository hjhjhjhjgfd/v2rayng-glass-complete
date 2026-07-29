package com.v2ray.ang.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * OPTIMIZED backdrop - adapts to device performance.
 * Low-end: solid color, no blobs.
 * High-end: blurred color blobs.
 */
@Composable
fun GlassBackdrop(isDarkTheme: Boolean, modifier: Modifier = Modifier) {
    val base = if (isDarkTheme) Color(0xFF17171B) else Color(0xFFF4F2EF)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(base)
    ) {
        if (isLowEndDevice) {
            // No blobs at all - just the solid background
            return@Box
        }

        val accentGreen = if (isDarkTheme) Color(0xFF2F6F5C) else Color(0xFFA0F2D0)
        val accentOrange = if (isDarkTheme) Color(0xFF6F3800) else Color(0xFFFFE0C2)

        Box(
            Modifier
                .align(Alignment.TopStart)
                .offset((-70).dp, (-50).dp)
                .size(180.dp)
                .softBlobBlur(40.dp)
                .background(
                    accentGreen.copy(alpha = if (isDarkTheme) 0.25f else 0.45f),
                    CircleShape
                )
        )
        Box(
            Modifier
                .align(Alignment.BottomEnd)
                .offset(70.dp, 90.dp)
                .size(220.dp)
                .softBlobBlur(40.dp)
                .background(
                    accentOrange.copy(alpha = if (isDarkTheme) 0.20f else 0.50f),
                    CircleShape
                )
        )
    }
}
