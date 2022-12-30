package aithanasakis.com.android.ui.components

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FocusableGameButton(modifier: Modifier = Modifier, onClick: () -> Unit, text: String) {
    val interactionSource = remember { MutableInteractionSource() }
    val focused = interactionSource.collectIsFocusedAsState().value
    val backgroundColor = when {
        focused -> MaterialTheme.colors.primary
        else -> Color.LightGray
    }

    Button(
        modifier = modifier.focusable(interactionSource = interactionSource),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
    ) {
        Text(text = text)
    }
}