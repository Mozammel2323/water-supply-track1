package com.waterbabu.watertrack.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkMode
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = White,
    primaryContainer = PrimaryBlueLight,
    onPrimaryContainer = Black,
    secondary = SecondaryBlue,
    onSecondary = White,
    secondaryContainer = SecondaryBlueDark,
    onSecondaryContainer = White,
    tertiary = AccentGreen,
    onTertiary = White,
    tertiaryContainer = AccentGreen.copy(alpha = 0.2f),
    onTertiaryContainer = AccentGreen,
    error = ErrorRed,
    onError = White,
    errorContainer = ErrorRed.copy(alpha = 0.2f),
    onErrorContainer = ErrorRed,
    background = White,
    onBackground = Black,
    surface = SurfaceLight,
    onSurface = Black,
    surfaceVariant = LightGray,
    onSurfaceVariant = MediumGray,
    outline = MediumGray,
    outlineVariant = LightGray,
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlueLight,
    onPrimary = Black,
    primaryContainer = PrimaryBlueDark,
    onPrimaryContainer = PrimaryBlueLight,
    secondary = SecondaryBlue,
    onSecondary = Black,
    secondaryContainer = SecondaryBlueDark,
    onSecondaryContainer = SecondaryBlue,
    tertiary = AccentGreen,
    onTertiary = Black,
    tertiaryContainer = AccentGreen.copy(alpha = 0.3f),
    onTertiaryContainer = AccentGreen,
    error = ErrorRed,
    onError = Black,
    errorContainer = ErrorRed.copy(alpha = 0.3f),
    onErrorContainer = ErrorRed,
    background = SurfaceDark,
    onBackground = White,
    surface = DarkGray,
    onSurface = White,
    surfaceVariant = Black,
    onSurfaceVariant = MediumGray,
    outline = MediumGray,
    outlineVariant = DarkGray,
)

@Composable
fun WaterTrackTheme(
    darkTheme: Boolean = isSystemInDarkMode(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view)?.isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
