package com.example.proyecto_gimnasia_lucas.model

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PruebasList(
    datosUsuario: DatosUsuario,
    onItemClick: (Prueba, DatosUsuario) -> Unit,
    navigateToBack: () -> Unit
) {
    // Mapa de categorías con sus pruebas
    val categoriasPruebas = mapOf(
        "Fuerza" to listOf("Abdominales", "Lanzamiento Balon 2kg"),
        "Flexibilidad" to listOf("Flexibilidad"),
        "Velocidad" to listOf("Velocidad"),
        "Resistencia" to listOf("Test de Cooper"),
        "Agilidad" to emptyList(),
        "Coordinación" to emptyList()
    )

    // Lista de pruebas según la edad del usuario
    val pruebasDisponibles = when {
        datosUsuario.edad < 12 -> emptyList()
        datosUsuario.edad == 12 -> listOf(
            Prueba("Abdominales", "Prueba de abdominales", R.drawable.abdominales, "https://www.foodspring.es/magazine/ejercicio-de-abdominales"),
            Prueba("Flexibilidad", "Prueba de flexibilidad", R.drawable.flexibilidad, "https://www.naradigital.es/blog/detalle-noticias/3005/como-preparar-el-test-de-flexibilidad"),
            Prueba("Test de Cooper", "Prueba de cooper", R.drawable.test_cooper, "https://universidadeuropea.com/blog/test-cooper/#que-es-el-test-de-cooper")
        )
        datosUsuario.edad == 13 -> listOf(
            Prueba("Abdominales", "Prueba de abdominales", R.drawable.abdominales, "https://www.foodspring.es/magazine/ejercicio-de-abdominales"),
            Prueba("Flexibilidad", "Prueba de flexibilidad", R.drawable.flexibilidad, "https://www.naradigital.es/blog/detalle-noticias/3005/como-preparar-el-test-de-flexibilidad"),
            Prueba("Test de Cooper", "Prueba de cooper", R.drawable.test_cooper, "https://universidadeuropea.com/blog/test-cooper/#que-es-el-test-de-cooper")
        )
        datosUsuario.edad == 14 -> listOf(
            Prueba("Abdominales", "Prueba de abdominales", R.drawable.abdominales, "https://www.foodspring.es/magazine/ejercicio-de-abdominales"),
            Prueba("Flexibilidad", "Prueba de flexibilidad", R.drawable.flexibilidad, "https://www.naradigital.es/blog/detalle-noticias/3005/como-preparar-el-test-de-flexibilidad"),
            Prueba("Test de Cooper", "Prueba de cooper", R.drawable.test_cooper, "https://universidadeuropea.com/blog/test-cooper/#que-es-el-test-de-cooper"),
            Prueba("Velocidad", "Prueba de velocidad", R.drawable.velocidad, "https://es.wikipedia.org/wiki/400_metros")
        )
        datosUsuario.edad == 15 -> listOf(
            Prueba("Abdominales", "Prueba de abdominales", R.drawable.abdominales, "https://www.foodspring.es/magazine/ejercicio-de-abdominales"),
            Prueba("Flexibilidad", "Prueba de flexibilidad", R.drawable.flexibilidad, "https://www.naradigital.es/blog/detalle-noticias/3005/como-preparar-el-test-de-flexibilidad"),
            Prueba("Test de Cooper", "Prueba de cooper", R.drawable.test_cooper, "https://universidadeuropea.com/blog/test-cooper/#que-es-el-test-de-cooper"),
            Prueba("Velocidad", "Prueba de velocidad", R.drawable.velocidad, "https://es.wikipedia.org/wiki/400_metros"),
            Prueba("Lanzamiento Balon 2kg", "Prueba de lanzamiento de balón", R.drawable.balon, "https://www.bodytone.eu/fr/todo-lo-que-debes-saber-sobre-el-slam-ball/")
        )
        datosUsuario.edad >= 16 -> listOf(
            Prueba("Abdominales", "Prueba de abdominales", R.drawable.abdominales, "https://www.foodspring.es/magazine/ejercicio-de-abdominales"),
            Prueba("Flexibilidad", "Prueba de flexibilidad", R.drawable.flexibilidad, "https://www.naradigital.es/blog/detalle-noticias/3005/como-preparar-el-test-de-flexibilidad"),
            Prueba("Test de Cooper", "Prueba de cooper", R.drawable.test_cooper, "https://universidadeuropea.com/blog/test-cooper/#que-es-el-test-de-cooper"),
            Prueba("Velocidad", "Prueba de velocidad", R.drawable.velocidad, "https://es.wikipedia.org/wiki/400_metros"),
            Prueba("Lanzamiento Balon 2kg", "Prueba de lanzamiento de balón", R.drawable.balon, "https://www.bodytone.eu/fr/todo-lo-que-debes-saber-sobre-el-slam-ball/")
        )
        else -> emptyList()
    }

    // Agrupar las pruebas por categoría
    val pruebasPorCategoria = categoriasPruebas.mapValues { (_, listaPruebas) ->
        pruebasDisponibles.filter { it.nombre in listaPruebas }
    }.filterValues { it.isNotEmpty() } // Filtrar categorías sin pruebas disponibles

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Clasificación de Pruebas",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            pruebasPorCategoria.forEach { (categoria, pruebas) ->
                stickyHeader {
                    Text(
                        text = categoria,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3F51B5),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                items(pruebas) { prueba ->
                    ItemPrueba(
                        prueba = prueba,
                        datosUsuario = datosUsuario,
                        onItemClick = onItemClick
                    )
                }
            }
        }

        Button(onClick = { navigateToBack() }, modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
            Text(text = "Volver atrás")
        }
    }
}
