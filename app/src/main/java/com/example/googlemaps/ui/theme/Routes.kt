package com.example.googlemaps.ui.theme


    sealed class Routes(val route: String) {
        object LaunchScreen: Routes("LaunchScreen")
        object LoginScreen: Routes("LoginScreen")
        object MapScreen: Routes("MapScreen")
        object AddMarkerScreen: Routes("AddMarkerScreen")
        object MarkerScreenList: Routes("MarkerScreenList")
    }

