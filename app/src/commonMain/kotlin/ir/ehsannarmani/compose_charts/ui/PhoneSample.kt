package ir.ehsannarmani.compose_charts.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PhoneSample() {
    Row(
        modifier = Modifier.fillMaxSize()
    )
    {
        LineSample2()
    }
}