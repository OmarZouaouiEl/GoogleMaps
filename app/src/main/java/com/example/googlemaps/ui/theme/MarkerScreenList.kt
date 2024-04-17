package com.example.googlemaps.ui.theme

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.FirebaseStorage


@Composable
fun MarkerListScreen(navController: NavController) {
    val markerList = remember { mutableStateListOf<String>() }
    for (i in 1..10) {
        markerList.add("Marcador $i")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Lista de marcadores:")
        Spacer(modifier = Modifier.height(8.dp))
        // Mostrar la lista de marcadores
        for (marker in markerList) {
            Text(text = marker, modifier = Modifier.padding(vertical = 4.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Botón para agregar un nuevo marcador
        Button(
            onClick = {
                navController.navigate("AddMarkerScreen")
            }
        ) {
            Text(text = "Agregar marcador")
        }
    }
}
@Composable
fun selectImage(){
// Actividad de resultado para seleccionar una imagen
    val selectImage =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            // Actualizar la URI de la imagen seleccionada
            uri?.let {
                // Aquí podrías guardar la imagen en Firebase Storage y obtener su URL
            }
        }
}
