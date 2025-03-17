package com.dongze.drawerpushcontent

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.roundToInt
import androidx.compose.ui.util.lerp


@Composable
fun DrawerContainer(
    modifier: Modifier = Modifier,
    isDrawerOpened: Boolean = false,
    drawerWidth: Dp = 200.dp,
    onSwipe: (Boolean) -> Unit = {},
    content: @Composable () -> Unit
) {
    val transition = updateTransition(targetState = isDrawerOpened, label = "")

    val collapseFraction =
        transition.animateFloat(transitionSpec = { tween(durationMillis = 400) },
            label = ""
        ) { state ->
            when (state) {
                false -> 0f
                true -> 1f
            }
        }

    Layout(
        modifier = modifier.pointerInput(Unit) {
            detectHorizontalDragGestures { _, dragAmount ->
                if (!transition.isRunning) {
                    onSwipe(dragAmount > 0 && abs(dragAmount) > 15)
                }
            }
        },
        content = content
    ) { measurables, constraints ->
        val drawerPlaceable = runCatching {
            measurables[0].measure(
                Constraints.fixed(
                    drawerWidth.roundToPx(),
                    constraints.maxHeight
                )
            )
        }.getOrNull()
        val contentPlaceable = runCatching { measurables[1].measure(constraints) }.getOrNull()

        val drawerX = lerp(
            -drawerWidth.roundToPx().toFloat(),
            0f,
            collapseFraction.value
        ).roundToInt()

        val contentX = lerp(
            0f,
            drawerWidth.roundToPx().toFloat(),
            collapseFraction.value
        ).roundToInt()


        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight
        ) {
            drawerPlaceable?.place(x = drawerX, y = 0)
            contentPlaceable?.place(x = contentX, y = 0)
        }
    }
}