package com.example.cleanarchitecture.presentation.extension

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode.Reverse
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.cleanarchitecture.presentation.theme.Secondary20

// region Constants
private const val SHIMMER_DURATION_MILLIS = 1300L
private const val SHIMMER_GRADIENT_START_COLOR_ALPHA = 0.4f
private const val SHIMMER_GRADIENT_MID_COLOR_ALPHA = 0.9f
private const val SHIMMER_GRADIENT_END_COLOR_ALPHA = 0.4f
private const val SHIMMER_TRANSLATION_INITIAL_VALUE = -200f
private const val SHIMMER_TRANSLATION_TARGET_VALUE = 1200f
// endregion

@Composable
fun Modifier.shimmer(
    durationMillis: Long = SHIMMER_DURATION_MILLIS,
    colors: List<Color> = listOf(
        Secondary20.copy(alpha = SHIMMER_GRADIENT_START_COLOR_ALPHA),
        Secondary20.copy(alpha = SHIMMER_GRADIENT_MID_COLOR_ALPHA),
        Secondary20.copy(alpha = SHIMMER_GRADIENT_END_COLOR_ALPHA),
    )
): Modifier {
    val infiniteTransition = rememberInfiniteTransition()
    val translateAnim = infiniteTransition.animateFloat(
        initialValue = SHIMMER_TRANSLATION_INITIAL_VALUE,
        targetValue = SHIMMER_TRANSLATION_TARGET_VALUE,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis.toInt(),
                easing = LinearEasing
            ),
            repeatMode = Reverse
        ),
    )
    val brush = Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    return this.background(brush)
}