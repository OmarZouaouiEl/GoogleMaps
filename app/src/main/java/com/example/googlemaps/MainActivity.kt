package com.example.googlemaps.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.googlemaps.ui.theme.GoogleMapsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleMapsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                }
            }
            val navigationController = rememberNavController()
            NavHost(
                navController = navigationController,
                startDestination = Routes.LaunchScreen.route
            ) {
                composable(Routes.LaunchScreen.route) { SplashScreen(navigationController) }
                composable(Routes.LoginScreen.route) { LoginScreen(navigationController) }
                composable(Routes.MapScreen.route) { MapScreen(navigationController) }
            }

        }

    }
}
