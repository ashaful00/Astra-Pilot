package com.ashraful.autopilot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ashraful.autopilot.ui.navigation.AppNavigation
import com.ashraful.autopilot.ui.theme.AshrafulAIAutopilotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AshrafulAIAutopilotTheme {
                AppNavigation()
            }
        }
    }
}
