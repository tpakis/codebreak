package aithanasakis.com.android.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FocusableColourCircle(
    modifier: Modifier = Modifier,
    diameter: Dp = 100.dp,
    borderColor: Color,
    fillColor: Color,
    onFocusChange: (Boolean) -> Unit = {},
    onClick: () -> Unit = {},
) {
    val focusRequester = FocusRequester()
    val interactionSource = remember { MutableInteractionSource() }
    val focused = interactionSource.collectIsFocusedAsState().value
    val boxBorderModifier = if (focused) {
        Modifier.border(BorderStroke(2.dp, Color.Gray))
    } else {
        Modifier
    }

    Box(modifier = boxBorderModifier) {
        ColouredCircle(
            modifier = modifier
                .focusRequester(focusRequester)
                .onFocusChanged { state -> onFocusChange(state.isFocused) }
                .focusable(interactionSource = interactionSource)
                .clickable {
                    if (!focused) {
                        focusRequester.requestFocus()
                    }
                    onClick()
                },
            diameter = diameter,
            borderColor = borderColor,
            fillColor = fillColor,
        )
    }

}