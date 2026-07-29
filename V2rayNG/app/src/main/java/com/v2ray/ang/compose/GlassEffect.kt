package com.v2ray.ang.compose

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * OPTIMIZED frosted glass - adapts to device performance.
 * Low-end devices get solid panels (no blur, high opacity = fast).
 * High-end devices get real glassmorphism.
 */
fun Modifier.glassPanel(
    shape: Shape = RoundedCornerShape(20.dp),
    isDarkTheme: Boolean = false
): Modifier {
    // On low-end: use solid-ish panel (no transparency processing)
    if (isLowEndDevice) {
        val fill = if (isDarkTheme) Color(0xFF1E1E24) else Color(0xFFE8E6E3)
        val border = if (isDarkTheme) Color.White.copy(alpha = 0.06f) else Color.Black.copy(alpha = 0.04f)
        return this
            .clip(shape)
            .background(fill)
            .border(width = 0.5.dp, color = border, shape = shape)
    }

    // High-end: real glass
    val fillTop = if (isDarkTheme) Color.White.copy(alpha = 0.08f) else Color.White.copy(alpha = 0.45f)
    val fillBottom = if (isDarkTheme) Color.White.copy(alpha = 0.03f) else Color.White.copy(alpha = 0.20f)
    val borderColor = if (isDarkTheme) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.55f)

    return this
        .clip(shape)
        .background(Brush.verticalGradient(listOf(fillTop, fillBottom)))
        .border(width = 1.dp, color = borderColor, shape = shape)
}

/**
 * OPTIMIZED blob blur - only on API 31+ AND high-end devices.
 * Low-end: no blur at all (just solid circles, barely visible).
 */
fun Modifier.softBlobBlur(radius: Dp = 40.dp): Modifier =
    if (shouldUseBlur) this.blur(radius) else this

/**
 * CHEAP glass panel - no blur, just translucent fill.
 * Use this on small UI elements (buttons, chips, small cards).
 */
fun Modifier.lightGlass(
    shape: Shape = RoundedCornerShape(16.dp),
    isDarkTheme: Boolean = false
): Modifier {
    if (isLowEndDevice) {
        val fill = if (isDarkTheme) Color(0xFF2A2A30) else Color(0xFFDEDCDA)
        return this.clip(shape).background(fill)
    }
    val fill = if (isDarkTheme) Color.White.copy(alpha = 0.06f) else Color.White.copy(alpha = 0.30f)
    val border = if (isDarkTheme) Color.White.copy(alpha = 0.08f) else Color.White.copy(alpha = 0.40f)
    return this
        .clip(shape)
        .background(fill)
        .border(width = 0.5.dp, color = border, shape = shape)
}
