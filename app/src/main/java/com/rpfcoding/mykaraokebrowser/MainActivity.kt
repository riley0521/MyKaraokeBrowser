package com.rpfcoding.mykaraokebrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import com.rpfcoding.mykaraokebrowser.presentation.home.NavGraphs
import com.rpfcoding.mykaraokebrowser.ui.theme.MyKaraokeBrowserTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyKaraokeBrowserTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}