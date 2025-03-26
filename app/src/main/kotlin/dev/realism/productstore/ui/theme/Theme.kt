package dev.realism.productstore.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView

private val LightColorScheme = lightColorScheme()

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ProductStoreTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = LightColorScheme
    LocalView.current.isForceDarkAllowed = false
    MaterialTheme(
        colorScheme = colors,
        content = content,
        typography = Typography
    )
}