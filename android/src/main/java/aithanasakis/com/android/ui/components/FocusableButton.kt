package aithanasakis.com.android.ui.components

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color

@Composable
fun FocusableGameButton(modifier: Modifier = Modifier, onClick: () -> Unit, text: String) {
    var focused by remember { mutableStateOf(false) }
    val backgroundColor = if (focused) Color.Cyan else Color.DarkGray
    Button(
        modifier = modifier
            .focusable(true)
            .onFocusChanged {focused = it.isFocused }
            .background(color = backgroundColor),
        onClick = onClick
    ) {
        Text(text = text)
    }
}