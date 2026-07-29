package com.v2ray.ang.compose

import android.os.Build

// Performance detection — lazy, safe, minimal
val isLowEndDevice: Boolean by lazy {
    try {
        Runtime.getRuntime().availableProcessors() < 4 || Build.VERSION.SDK_INT < 24
    } catch (e: Exception) { true }
}

// No blur anywhere — all gradient-based
val shouldUseBlur: Boolean = false
