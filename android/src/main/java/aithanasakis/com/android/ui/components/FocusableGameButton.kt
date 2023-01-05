package aithanasakis.com.android.ui.components

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.TextUnit

@Composable
fun FocusableGameButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    fontSize: TextUnit = MaterialTheme.typography.button.fontSize,
    enabled: Boolean = true,
    focusRequester: FocusRequester = FocusRequester(),
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focused = interactionSource.collectIsFocusedAsState().value
    val backgroundColor = when {
        focused -> MaterialTheme.colors.primary
        else -> MaterialTheme.colors.background
    }

    Button(
        modifier = modifier
            .focusRequester(focusRequester)
            .focusable(enabled = enabled, interactionSource = interactionSource),
        onClick = {
            onClick()
            focusRequester.requestFocus()
        },
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
    ) {
        Text(text = text, fontSize = fontSize)
    }
}