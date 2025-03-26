package dev.realism.productstore

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import dev.realism.productstore.productlistscreen.presentation.ProductListScreen
import dev.realism.productstore.productlistscreen.presentation.ProductListScreenViewModel
import dev.realism.productstore.ui.theme.ProductStoreTheme
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject


class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: ProductListScreenViewModel

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as ProductStoreApp).appComponent.inject(this)
        enableEdgeToEdge()
        if (isMiUi()) setMIUISettings()
        setContent {
            ProductStoreTheme(darkTheme = false, dynamicColor = false) {
                ProductListScreen(viewModel)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @Suppress("DEPRECATION")
    private fun setMIUISettings() {
        Log.d("VIEWMODEL", "IS MIUI")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.blue)
        window.isNavigationBarContrastEnforced = false
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }

    private fun isMiUi(): Boolean {
        fun getSystemProperty(propName: String): String? {
            val line: String
            var input: BufferedReader? = null
            try {
                val p = Runtime.getRuntime().exec("getprop $propName")
                input = BufferedReader(InputStreamReader(p.inputStream), 1024)
                line = input.readLine()
                input.close()
            } catch (ex: IOException) {
                return null
            } finally {
                if (input != null) {
                    try {
                        input.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            return line
        }
        return !TextUtils.isEmpty(getSystemProperty("ro.miui.ui.version.name"))
    }
}