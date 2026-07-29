package com.v2ray.ang.compose

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

/**
 * Performance configuration for low-end devices.
 * Detects weak CPUs and reduces effects accordingly.
 */

// Detection: devices with < 4 cores or API < 24 are "low-end"
val isLowEndDevice: Boolean by lazy {
    val cores = Runtime.getRuntime().availableProcessors()
    val api = android.os.Build.VERSION.SDK_INT
    cores < 4 || api < 24
}

// Animation spec: fast spring for high-end, snap for low-end
@Composable
fun fastSpring<Float>(): SpringSpec<Float> = spring(
    dampingRatio = Spring.DampingRatioMediumBouncy,
    stiffness = if (isLowEndDevice) Spring.StiffnessHigh else Spring.StiffnessMedium
)

// Short tween for low-end
@Composable
fun fastTween(): TweenSpec<Float> = tween(
    durationMillis = if (isLowEndDevice) 100 else 250
)

// Dp animation
@Composable
fun fastDpSpring(): SpringSpec<Dp> = spring(
    dampingRatio = Spring.DampingRatioNoBouncy,
    stiffness = if (isLowEndDevice) Spring.StiffnessHigh else Spring.StiffnessMedium
)

/**
 * Reduced motion: disable blur entirely on very old devices
 */
val shouldUseBlur: Boolean by lazy {
    android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S && !isLowEndDevice
}

/**
 * Should animate pager transitions
 */
val shouldAnimatePager: Boolean by lazy {
    !isLowEndDevice
}

/**
 * Glass panel opacity (higher on low-end = more solid = faster)
 */
fun glassOpacity(isDark: Boolean): Float {
    return if (isLowEndDevice) {
        if (isDark) 0.85f else 0.90f  // More opaque = less blur needed
    } else {
        if (isDark) 0.08f else 0.45f  // Default glass transparency
    }
}
