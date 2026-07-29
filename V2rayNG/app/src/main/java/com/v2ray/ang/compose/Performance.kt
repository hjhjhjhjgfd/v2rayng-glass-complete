package com.v2ray.ang.compose

import android.os.Build

/**
 * Performance configuration for low-end devices.
 * Detects weak CPUs and reduces effects accordingly.
 * All values are lazy-initialized at first access to avoid startup overhead.
 */

// Detection: devices with < 4 cores or old API are "low-end"
val isLowEndDevice: Boolean by lazy {
    try {
        val cores = Runtime.getRuntime().availableProcessors()
        cores < 4 || Build.VERSION.SDK_INT < 24
    } catch (e: Exception) {
        true  // Assume low-end on error (safer)
    }
}

// Blur only on API 31+ (RenderEffect) AND high-end devices
val shouldUseBlur: Boolean by lazy {
    try {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !isLowEndDevice
    } catch (e: Exception) {
        false
    }
}
