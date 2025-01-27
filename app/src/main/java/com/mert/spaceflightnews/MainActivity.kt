package com.mert.spaceflightnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mert.spaceflightnews.presentation.ui.MainScreen
import com.mert.spaceflightnews.ui.theme.SpaceflightNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceflightNewsTheme {
                MainScreen()
            }
        }
    }
}