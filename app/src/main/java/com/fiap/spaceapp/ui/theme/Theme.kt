package com.fiap.spaceapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val SpaceBlack   = Color(0xFF0A0E1A)
val SpaceDark    = Color(0xFF111827)
val SpaceCard    = Color(0xFF1C2333)
val SpacePrimary = Color(0xFF4FC3F7)
val SpaceAccent  = Color(0xFF81C784)
val SpaceWarning = Color(0xFFFFB74D)
val SpaceDanger  = Color(0xFFEF5350)
val SpaceText    = Color(0xFFE0E6F0)
val SpaceSubtext = Color(0xFF8B9DC3)

private val DarkColors = darkColorScheme(
    primary        = SpacePrimary,
    background     = SpaceBlack,
    surface        = SpaceCard,
    onPrimary      = SpaceBlack,
    onBackground   = SpaceText,
    onSurface      = SpaceText,
    secondary      = SpaceAccent,
    tertiary       = SpaceWarning
)

@Composable
fun SpaceAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColors,
        content     = content
    )
}
