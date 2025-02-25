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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_gimnasia_lucas.R


@Composable
fun ItemPrueba(prueba: Prueba, onItemClick: (Prueba) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(prueba) }
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

            Column(
                modifier = Modifier.weight(1f)
            ) {
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
fun PruebasList(onItemClick: (Prueba) -> Unit, navigateToBack: () -> Unit) {
    val pruebas = listOf(
        Prueba(
            "Abdominales",
            "Prueba de abdominales",
            R.drawable.abdominales,
            "https://www.foodspring.es/magazine/ejercicio-de-abdominales"
        ),
        Prueba(
            "Flexibilidad",
            "Prueba de flexibilidad",
            R.drawable.flexibilidad,
            "https://www.naradigital.es/blog/detalle-noticias/3005/como-preparar-el-test-de-flexibilidad"
        ),
        Prueba(
            "Test de Cooper",
            "Prueba de cooper",
            R.drawable.test_cooper,
            "https://universidadeuropea.com/blog/test-cooper/#que-es-el-test-de-cooper"
        ),
        Prueba(
            "Velocidad",
            "Prueba de velocidad",
            R.drawable.velocidad,
            "https://es.wikipedia.org/wiki/400_metros"
        ),
        Prueba(
            "Lanzamiento Balon 2kg",
            "Prueba de lanzamiento de balón",
            R.drawable.balon,
            "https://www.bodytone.eu/fr/todo-lo-que-debes-saber-sobre-el-slam-ball/"
        )
    )

    var palabraInsertada by remember { mutableStateOf("") }

    val filteredPruebas = pruebas.filter {
        it.nombre.contains(palabraInsertada, ignoreCase = true)
    }

    Spacer(modifier = Modifier.height(10.dp))

    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            value = palabraInsertada,
            onValueChange = { palabraInsertada = it },
            label = { Text("Buscar por nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(60.dp),
            singleLine = true
        )

        LazyColumn(
            modifier = Modifier.weight(1f) ) {
            items(filteredPruebas) { prueba ->
                ItemPrueba(prueba = prueba, onItemClick = onItemClick)
            }
        }

        Box(contentAlignment = Alignment.BottomStart,
            modifier = Modifier.fillMaxWidth().weight(0.1f) ) {
            Button(onClick = { navigateToBack() }) {
                Text(text = "Volver atrás")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PruebasListPreview() {
}
