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
 * Decorative backdrop of soft, blurred color blobs behind a solid base
 * color. Pair this with [glassPanel] on the surfaces drawn above it (top
 * bar, bottom bar, cards) to create a glassmorphism effect: the blobs are
 * what the translucent panels "reveal" through their transparency.
 */
@Composable
fun GlassBackdrop(isDarkTheme: Boolean, modifier: Modifier = Modifier) {
    val base = if (isDarkTheme) Color(0xFF17171B) else Color(0xFFF4F2EF)
    val accentGreen = if (isDarkTheme) Color(0xFF2F6F5C) else Color(0xFFA0F2D0)
    val accentOrange = if (isDarkTheme) Color(0xFF6F3800) else Color(0xFFFFE0C2)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(base)
    ) {
        Box(
            Modifier
                .align(Alignment.TopStart)
                .offset((-70).dp, (-50).dp)
                .size(220.dp)
                .softBlobBlur(70.dp)
                .background(
                    accentGreen.copy(alpha = if (isDarkTheme) 0.35f else 0.55f),
                    CircleShape
                )
        )
        Box(
            Modifier
                .align(Alignment.BottomEnd)
                .offset(70.dp, 90.dp)
                .size(280.dp)
                .softBlobBlur(80.dp)
                .background(
                    accentOrange.copy(alpha = if (isDarkTheme) 0.30f else 0.6f),
                    CircleShape
                )
        )
    }
}
