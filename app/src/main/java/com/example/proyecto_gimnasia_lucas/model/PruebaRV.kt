package com.example.proyecto_gimnasia_lucas.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_gimnasia_lucas.DatosUsuario
import com.example.proyecto_gimnasia_lucas.R

@Composable
fun ItemPrueba(prueba: Prueba, datosUsuario: DatosUsuario, onItemClick: (Prueba, DatosUsuario) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(prueba, datosUsuario) }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = prueba.imagenResId),
                contentDescription = "Imagen de la prueba",
                modifier = Modifier
                    .size(120.dp)
                    .padding(end = 16.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = prueba.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF3F51B5)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = prueba.descripcion,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun PruebasList(
    datosUsuario: DatosUsuario,
    onItemClick: (Prueba, DatosUsuario) -> Unit,
    navigateToBack: () -> Unit
) {
    val pruebas = when {
        datosUsuario.edad < 12 -> null
        datosUsuario.edad == 12 -> listOf(
            Prueba("Abdominales", "Prueba de abdominales", R.drawable.abdominales, ""),
            Prueba("Flexibilidad", "Prueba de flexibilidad", R.drawable.flexibilidad, ""),
            Prueba("Test de Cooper", "Prueba de cooper", R.drawable.test_cooper, "")
        )
        datosUsuario.edad == 13 -> listOf(
            Prueba("Abdominales", "Prueba de abdominales", R.drawable.abdominales, ""),
            Prueba("Flexibilidad", "Prueba de flexibilidad", R.drawable.flexibilidad, ""),
            Prueba("Test de Cooper", "Prueba de cooper", R.drawable.test_cooper, "")
        )
        datosUsuario.edad == 14 -> listOf(
            Prueba("Abdominales", "Prueba de abdominales", R.drawable.abdominales, ""),
            Prueba("Flexibilidad", "Prueba de flexibilidad", R.drawable.flexibilidad, ""),
            Prueba("Test de Cooper", "Prueba de cooper", R.drawable.test_cooper, ""),
            Prueba("Velocidad", "Prueba de velocidad", R.drawable.velocidad, "")
        )
        datosUsuario.edad == 15 -> listOf(
            Prueba("Abdominales", "Prueba de abdominales", R.drawable.abdominales, ""),
            Prueba("Flexibilidad", "Prueba de flexibilidad", R.drawable.flexibilidad, ""),
            Prueba("Test de Cooper", "Prueba de cooper", R.drawable.test_cooper, ""),
            Prueba("Velocidad", "Prueba de velocidad", R.drawable.velocidad, ""),
            Prueba("Lanzamiento Balon 2kg", "Prueba de lanzamiento de balón", R.drawable.balon, "")
        )
        datosUsuario.edad >= 16 -> listOf(
            Prueba("Abdominales", "Prueba de abdominales", R.drawable.abdominales, ""),
            Prueba("Flexibilidad", "Prueba de flexibilidad", R.drawable.flexibilidad, ""),
            Prueba("Test de Cooper", "Prueba de cooper", R.drawable.test_cooper, ""),
            Prueba("Velocidad", "Prueba de velocidad", R.drawable.velocidad, ""),
            Prueba("Lanzamiento Balon 2kg", "Prueba de lanzamiento de balón", R.drawable.balon, "")
        )

        else -> null
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (pruebas == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Introduce una edad válida (+12)",
                    fontSize = 18.sp,
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(pruebas) { prueba ->
                    ItemPrueba(
                        prueba = prueba,
                        datosUsuario = datosUsuario,
                        onItemClick = { pruebaSeleccionada, datosUsuario ->
                            onItemClick(pruebaSeleccionada, datosUsuario)
                        }
                    )
                }
            }
        }

        Button(onClick = { navigateToBack() }) {
            Text(text = "Volver atrás")
        }
    }

}
