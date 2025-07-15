package com.sargis.khlopuzyan.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.sargis.khlopuzyan.presentation.ui.main.navigation.MainNavigation
import com.sargis.khlopuzyan.presentation.ui.theme.PL_JetpackComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PL_JetpackComposeTheme {
                MainNavigation()
            }
        }
    }
}