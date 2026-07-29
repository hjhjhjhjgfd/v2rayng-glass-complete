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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * PLUTO 2027 Backdrop — radial gradient blobs. No blur. GPU-cheap.
 */
@Composable
fun GlassBackdrop(isDarkTheme: Boolean, modifier: Modifier = Modifier) {
    val base = if (isDarkTheme) Color(0xFF0F0F1E) else Color(0xFFF8F6FF)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(base)
    ) {
        Box(Modifier
            .align(Alignment.TopStart)
            .offset((-80).dp, (-60).dp)
            .size(220.dp)
            .background(Brush.radialGradient(listOf(glassAccent.copy(alpha = 0.18f), Color.Transparent)), CircleShape)
        )
        Box(Modifier
            .align(Alignment.TopEnd)
            .offset(60.dp, 120.dp)
            .size(180.dp)
            .background(Brush.radialGradient(listOf(glassSuccess.copy(alpha = 0.12f), Color.Transparent)), CircleShape)
        )
        Box(Modifier
            .align(Alignment.BottomStart)
            .offset(40.dp, (-100).dp)
            .size(200.dp)
            .background(Brush.radialGradient(listOf(Color(0xFFFF6B9D).copy(alpha = 0.10f), Color.Transparent)), CircleShape)
        )
        Box(Modifier
            .align(Alignment.Center)
            .offset(0.dp, (-40).dp)
            .size(160.dp)
            .background(Brush.radialGradient(listOf(Color(0xFF60A5FA).copy(alpha = 0.08f), Color.Transparent)), CircleShape)
        )
    }
}