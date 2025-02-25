package com.example.proyecto_gimnasia_lucas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaDetallePrueba(
    navigateBack: () -> Unit,
    nombre: String
) {

    val pruebasDetalles = mapOf(
        "Abdominales" to "Prueba que evalúa la capacidad de realizar abdominales.",
        "Flexibilidad" to "Prueba que mide la capacidad de flexibilidad del cuerpo.",
        "Test de Cooper" to "Prueba de resistencia cardiovascular.",
        "Velocidad" to "Prueba que evalúa la rapidez del corredor.",
        "Lanzamiento Balon 2kg" to "Prueba de fuerza explosiva usando un balón de 2kg."
    )


    val descripcionPrueba = pruebasDetalles[nombre] ?: "Descripción no disponible"

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = nombre,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = descripcionPrueba,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = { navigateBack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Volver atrás")
        }
    }
}
