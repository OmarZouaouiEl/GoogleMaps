package com.example.googlemaps.ui.theme

import android.widget.Button
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.mlkit.vision.text.Text

@Composable
fun MapScreen(navController: NavController) {
    var mapa: GoogleMap? by remember { mutableStateOf(null) }
    var mapView: MapView? by remember { mutableStateOf(null) }
    var marker: Marker? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // MapView para mostrar el mapa
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            MapViewContainer(mapView) { googleMap ->
                mapa = googleMap
            }
        }

        Surface(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally),
            color = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onSecondary,
            elevation = 4.dp
        ) {
            Button(
                onClick = {
                    val currentMap = mapa
                    if (currentMap != null) {
                        val currentLatLng = currentMap.cameraPosition.target
                        marker?.remove() // Eliminar marcador anterior si existe
                        marker = currentMap.addMarker(
                            MarkerOptions()
                                .position(LatLng(currentLatLng.latitude, currentLatLng.longitude))
                                .title("Nuevo marcador")
                        )
                    }
                },
                modifier = Modifier.fillMaxSize(),
                shape = CircleShape
            ) {
                Text(text = "+", modifier = Modifier.padding(8.dp))
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
