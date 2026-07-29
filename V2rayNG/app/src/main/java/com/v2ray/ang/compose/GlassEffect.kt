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
 * Applies a "frosted glass" look to any composable: a translucent gradient
 * fill, a soft light border and rounded corners. Meant to be layered on top
 * of [GlassBackdrop] (or any colorful background) so the panel underneath
 * shows through while the content on top stays readable.
 */
fun Modifier.glassPanel(
    shape: Shape = RoundedCornerShape(20.dp),
    isDarkTheme: Boolean = false
): Modifier {
    val fillTop = if (isDarkTheme) Color.White.copy(alpha = 0.10f) else Color.White.copy(alpha = 0.55f)
    val fillBottom = if (isDarkTheme) Color.White.copy(alpha = 0.04f) else Color.White.copy(alpha = 0.25f)
    val borderColor = if (isDarkTheme) Color.White.copy(alpha = 0.16f) else Color.White.copy(alpha = 0.65f)

    return this
        .clip(shape)
        .background(Brush.verticalGradient(listOf(fillTop, fillBottom)))
        .border(width = 1.dp, color = borderColor, shape = shape)
}

/**
 * Soft blur used to build blurred color blobs for a glassmorphism backdrop.
 * Real-time blur (RenderEffect) is only available on API 31+; on older
 * devices this is a no-op and the blob is shown as a plain soft circle,
 * which still reads fine as a decorative backdrop.
 */
fun Modifier.softBlobBlur(radius: Dp = 60.dp): Modifier =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) this.blur(radius) else this
