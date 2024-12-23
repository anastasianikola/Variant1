package com.example.gpslocator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gpslocator.ui.theme.GPSLocatorTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GPSLocatorTheme {
                val context = LocalContext.current
                val navHost = rememberNavController()

                NavHost(navController = navHost, startDestination = "start") {
                    composable("start") {
                        Start(navHost, context)
                    }
                    composable("foto") {
                        Foto(navHost)
                    }
                }
            }
        }
    }
}
