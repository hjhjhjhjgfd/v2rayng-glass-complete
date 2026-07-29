package com.v2ray.ang.compose

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// ═══════════════════════════════════════════════════════════════
// PLUTO GLASS 2027 — Modern, Fast, Beautiful
// No real blur (GPU-heavy). Uses gradient + shadow simulation.
// Works perfectly on old devices (A520F) and new ones alike.
// ═══════════════════════════════════════════════════════════════

// Color palette — cosmic dark theme
val glassBgDark = Color(0xFF1C1C2E)
val glassBgDark2 = Color(0xFF252540)
val glassBgLight = Color(0xFFF8F6FF)
val glassBgLight2 = Color(0xFFEEEAF8)
val glassAccent = Color(0xFF7C5CFF)
val glassAccentDim = Color(0xFF4A3A8E)
val glassSuccess = Color(0xFF34D399)
val glassError = Color(0xFFEF4444)
val glassTextDark = Color(0xFFF0EEFF)
val glassTextLight = Color(0xFF1C1C2E)
val glassBorderDark = Color.White.copy(alpha = 0.08f)
val glassBorderLight = Color.Black.copy(alpha = 0.05f)

/**
 * Modern glass panel — gradient + soft shadow, NO blur.
 * Looks like glass, performs like solid. Zero GPU overhead.
 */
fun Modifier.glassPanel(
    shape: Shape = RoundedCornerShape(20.dp),
    isDarkTheme: Boolean = true,
    elevation: Dp = 2.dp
): Modifier {
    val bg = if (isDarkTheme) glassBgDark else glassBgLight
    val bg2 = if (isDarkTheme) glassBgDark2 else glassBgLight2
    val border = if (isDarkTheme) glassBorderDark else glassBorderLight

    return this
        .shadow(elevation = elevation, shape = shape, clip = false)
        .clip(shape)
        .background(Brush.linearGradient(listOf(bg, bg2)))
        .border(width = 1.dp, color = border, shape = shape)
}

/**
 * Accent glass — for active/selected items. Purple glow.
 */
fun Modifier.accentGlass(
    shape: Shape = RoundedCornerShape(16.dp),
    isDarkTheme: Boolean = true
): Modifier {
    val bg = if (isDarkTheme) glassAccent.copy(alpha = 0.15f) else glassAccent.copy(alpha = 0.08f)
    val border = glassAccent.copy(alpha = 0.4f)
    return this
        .clip(shape)
        .background(bg)
        .border(width = 1.dp, color = border, shape = shape)
}

/**
 * Surface glass — for cards, sheets. Slightly transparent.
 */
fun Modifier.surfaceGlass(
    shape: Shape = RoundedCornerShape(24.dp),
    isDarkTheme: Boolean = true
): Modifier {
    val bg = if (isDarkTheme) Color(0xFF20203A) else Color(0xFFF2F0FA)
    val border = if (isDarkTheme) glassBorderDark else glassBorderLight
    return this
        .clip(shape)
        .background(bg)
        .border(width = 0.5.dp, color = border, shape = shape)
}

/**
 * Floating action button glow effect.
 */
fun Modifier.fabGlow(isDarkTheme: Boolean = true): Modifier {
    val glow = if (isDarkTheme) glassAccent.copy(alpha = 0.3f) else glassAccent.copy(alpha = 0.15f)
    return this
        .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp), clip = false)
}

/**
 * Soft blob blur — NO REAL BLUR. Just decorative circle.
 */
fun Modifier.softBlobBlur(radius: Dp = 40.dp): Modifier = this
