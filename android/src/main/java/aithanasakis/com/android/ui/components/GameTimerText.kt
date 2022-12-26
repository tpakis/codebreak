package aithanasakis.com.android.ui.components

import aithanasakis.com.android.ui.theme.BreakCodeTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AvTimer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@Composable
fun GameTimerText(modifier: Modifier = Modifier, fontSize: TextUnit = TextUnit.Unspecified, remainingMillis: Long) {
    val minutes = remainingMillis / 1.seconds.inWholeMilliseconds / 1.minutes.inWholeSeconds
    val seconds = remainingMillis / 1.seconds.inWholeMilliseconds % 1.minutes.inWholeSeconds
    val inlineContentId = "inlineContentId"
    val text = buildAnnotatedString {
        append("${minutes.toTwoDigits()} : ${seconds.toTwoDigits()} ")
        appendInlineContent(inlineContentId, "alternate")
    }

    val inlineContent = mapOf(
        Pair(inlineContentId,
            InlineTextContent(
                Placeholder(
                    width = fontSize,
                    height = fontSize,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                )
            ) {
                Icon(Icons.Rounded.AvTimer, "Remaining Game Time", Modifier.fillMaxSize())
            }
        )
    )
    Text(text, fontSize = fontSize, inlineContent = inlineContent, modifier = modifier)
}

private fun Long.toTwoDigits(): String = if (this in (-9..9)) "0$this" else "$this"

@Preview
@Composable
fun GameTimerTextPreview() {
    BreakCodeTheme {
        GameTimerText(fontSize = 50.sp, remainingMillis = 62120)
    }
}