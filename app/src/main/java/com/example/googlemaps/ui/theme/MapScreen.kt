package com.example.googlemaps.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

@Composable
fun MapScreen(navController: NavController) {
    var mapa: GoogleMap? by remember { mutableStateOf(null) }
    var mapView: MapView? by remember { mutableStateOf(null) }
    var marker: Marker? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // MapView to show the map
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            MapViewContainer(mapView) { googleMap ->
                mapa = googleMap
            }
        }

        val buttonColor = Color.Blue // Define your color here

        Surface(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.CenterHorizontally),
            color = buttonColor,
            contentColor = Color.White
        ) {
            Button(onClick = {
                val currentMap = mapa
                if (currentMap != null) {
                    val currentLatLng = currentMap.cameraPosition.target
                    marker?.remove()
                    marker = currentMap.addMarker(
                        MarkerOptions()
                            .position(LatLng(currentLatLng.latitude, currentLatLng.longitude))
                            .title("New marker")
                    )
                }
            },
                modifier = Modifier.fillMaxSize(),
            )
        {

            }
        }
    }
}

@Composable
fun MapViewContainer(
    mapView: MapView?,
    onMapReady: (GoogleMap?) -> Unit
) {
    AndroidView(
        factory = { context ->
            mapView ?: MapView(context).also { map ->
                map.getMapAsync { googleMap ->
                    onMapReady(googleMap)
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { mapView ->
        if (mapView != null && mapView.parent == null) {
            mapView.onCreate(null)
            mapView.onResume()
        }
    }
}
