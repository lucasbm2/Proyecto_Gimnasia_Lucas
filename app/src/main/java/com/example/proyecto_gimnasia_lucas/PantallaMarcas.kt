package com.example.proyecto_gimnasia_lucas


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
import com.example.proyecto_gimnasia_lucas.database.EntMarcas
import com.example.proyecto_gimnasia_lucas.model.Prueba
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MostrarDatos(
    datosUsuario: DatosUsuario?,
    prueba: Prueba?,
    navigateToBack: () -> Unit
) {
    var marca by remember { mutableStateOf("") }
    var nota by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (prueba?.nombre) {
            "Abdominales" -> {
                Image(
                    painter = painterResource(id = R.drawable.abdominales),
                    contentDescription = "Prueba de Abdominales",
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = "PRUEBA DE ABDOMINALES",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text("Introduce la cantidad de abdominales:", color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(16.dp))
            }

            "Flexibilidad" -> {
                Image(
                    painter = painterResource(id = R.drawable.flexibilidad),
                    contentDescription = "Prueba de Flexibilidad",
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = "PRUEBA DE FLEXIBILIDAD",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text("Introduce la distancia en cm:", color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(16.dp))
            }

            "Test de Cooper" -> {
                Image(
                    painter = painterResource(id = R.drawable.test_cooper),
                    contentDescription = "Test de Cooper",
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = "TEST DE COOPER",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text("Introduce la distancia en metros:", color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(16.dp))
            }

            "Velocidad" -> {
                Image(
                    painter = painterResource(id = R.drawable.velocidad),
                    contentDescription = "Prueba de Velocidad",
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = "PRUEBA DE VELOCIDAD",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text("Introduce el tiempo en segundos:", color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(16.dp))
            }

            "Lanzamiento Balon 2kg" -> {
                Image(
                    painter = painterResource(id = R.drawable.balon),
                    contentDescription = "Lanzamiento de Balón",
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = "PRUEBA DE LANZAMIENTO DE BALÓN",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text("Introduce la distancia del lanzamiento en metros:", color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        if (prueba != null) {
            TextField(
                value = marca,
                onValueChange = { marca = it },
                placeholder = { Text("Introduce la marca", color = MaterialTheme.colorScheme.onBackground) }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                datosUsuario?.let {
                    val resultado = marca.toFloatOrNull() ?: 0f
                    nota = when (prueba.nombre) {
                        "Abdominales" -> calcularNotaAbdominales(resultado, it)
                        "Flexibilidad" -> calcularNotaFlexibilidad(resultado, it)
                        "Test de Cooper" -> calcularNotaTestCooper(resultado, it)
                        "Velocidad" -> calcularNotaVelocidad(resultado, it)
                        "Lanzamiento Balon 2kg" -> calcularNotaLanzamiento(resultado, it)
                        else -> "N/A"
                    }
                }
            }) {
                Text("Calcular Nota")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (nota.isNotEmpty()) {
                Text(
                    text = "Nota obtenida: $nota",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = navigateToBack) {
                Text("Volver")
            }
        }
    }
}


fun calcularNotaAbdominales(resultado: Float, datosUsuario: DatosUsuario): String {
    return when (datosUsuario.genero) {
        "Hombre" -> when (datosUsuario.edad) {
            in 12..16 -> when (datosUsuario.edad) {
                12 -> when {
                    resultado >= 30 -> "10"
                    resultado >= 29 -> "9.5"
                    resultado >= 28 -> "9"
                    resultado >= 27 -> "8.5"
                    resultado >= 26 -> "8"
                    resultado >= 25 -> "7.5"
                    resultado >= 24 -> "7"
                    resultado >= 23 -> "6.5"
                    resultado >= 22 -> "6"
                    resultado >= 21 -> "5.5"
                    resultado >= 20 -> "5"
                    resultado >= 19 -> "4.5"
                    resultado >= 18 -> "4"
                    resultado >= 17 -> "3.5"
                    resultado >= 16 -> "3"
                    resultado >= 15 -> "2.5"
                    resultado >= 14 -> "2"
                    else -> "N/A"
                }

                13 -> when {
                    resultado >= 32 -> "10"
                    resultado >= 31 -> "9.5"
                    resultado >= 30 -> "9"
                    resultado >= 29 -> "8.5"
                    resultado >= 28 -> "8"
                    resultado >= 27 -> "7.5"
                    resultado >= 26 -> "7"
                    resultado >= 25 -> "6.5"
                    resultado >= 24 -> "6"
                    resultado >= 23 -> "5.5"
                    resultado >= 22 -> "5"
                    resultado >= 21 -> "4.5"
                    resultado >= 20 -> "4"
                    resultado >= 19 -> "3.5"
                    resultado >= 18 -> "3"
                    resultado >= 17 -> "2.5"
                    resultado >= 16 -> "2"
                    else -> "N/A"
                }

                14 -> when {
                    resultado >= 33 -> "10"
                    resultado >= 32 -> "9.5"
                    resultado >= 31 -> "9"
                    resultado >= 30 -> "8.5"
                    resultado >= 29 -> "8"
                    resultado >= 28 -> "7.5"
                    resultado >= 27 -> "7"
                    resultado >= 26 -> "6.5"
                    resultado >= 25 -> "6"
                    resultado >= 24 -> "5.5"
                    resultado >= 23 -> "5"
                    resultado >= 22 -> "4.5"
                    resultado >= 21 -> "4"
                    resultado >= 20 -> "3.5"
                    resultado >= 19 -> "3"
                    resultado >= 18 -> "2.5"
                    resultado >= 17 -> "2"
                    else -> "N/A"
                }

                15 -> when {
                    resultado >= 36 -> "10"
                    resultado >= 35 -> "9.5"
                    resultado >= 34 -> "9"
                    resultado >= 33 -> "8.5"
                    resultado >= 32 -> "8"
                    resultado >= 31 -> "7.5"
                    resultado >= 30 -> "7"
                    resultado >= 29 -> "6.5"
                    resultado >= 28 -> "6"
                    resultado >= 27 -> "5.5"
                    resultado >= 26 -> "5"
                    resultado >= 25 -> "4.5"
                    resultado >= 24 -> "4"
                    resultado >= 23 -> "3.5"
                    resultado >= 22 -> "3"
                    resultado >= 21 -> "2.5"
                    resultado >= 20 -> "2"
                    else -> "N/A"
                }

                16 -> when {
                    resultado >= 38 -> "10"
                    resultado >= 37 -> "9.5"
                    resultado >= 36 -> "9"
                    resultado >= 35 -> "8.5"
                    resultado >= 34 -> "8"
                    resultado >= 33 -> "7.5"
                    resultado >= 32 -> "7"
                    resultado >= 31 -> "6.5"
                    resultado >= 30 -> "6"
                    resultado >= 29 -> "5.5"
                    resultado >= 28 -> "5"
                    resultado >= 27 -> "4.5"
                    resultado >= 26 -> "4"
                    resultado >= 25 -> "3.5"
                    resultado >= 24 -> "3"
                    resultado >= 23 -> "2.5"
                    resultado >= 22 -> "2"
                    else -> "N/A"
                }

                else -> "N/A"
            }

            in 17..99 -> when {
                resultado >= 40 -> "10"
                resultado >= 38 -> "9.5"
                resultado >= 36 -> "9"
                resultado >= 34 -> "8.5"
                resultado >= 32 -> "8"
                resultado >= 30 -> "7.5"
                resultado >= 28 -> "7"
                resultado >= 26 -> "6.5"
                resultado >= 24 -> "6"
                resultado >= 22 -> "5.5"
                resultado >= 20 -> "5"
                resultado >= 18 -> "4.5"
                resultado >= 16 -> "4"
                resultado >= 14 -> "3.5"
                resultado >= 12 -> "3"
                resultado >= 10 -> "2.5"
                resultado >= 8 -> "2"
                else -> "N/A"
            }

            else -> "N/A"
        }

        "Mujer" -> when (datosUsuario.edad) {
            in 12..16 -> when (datosUsuario.edad) {
                12 -> when {
                    resultado >= 30 -> "10"
                    resultado >= 29 -> "9.5"
                    resultado >= 28 -> "9"
                    resultado >= 27 -> "8.5"
                    resultado >= 26 -> "8"
                    resultado >= 25 -> "7.5"
                    resultado >= 24 -> "7"
                    resultado >= 23 -> "6.5"
                    resultado >= 22 -> "6"
                    resultado >= 21 -> "5.5"
                    resultado >= 20 -> "5"
                    resultado >= 19 -> "4.5"
                    resultado >= 18 -> "4"
                    resultado >= 17 -> "3.5"
                    resultado >= 16 -> "3"
                    resultado >= 15 -> "2.5"
                    resultado >= 14 -> "2"
                    else -> "N/A"
                }

                else -> "N/A"
            }

            in 17..99 -> when {
                resultado >= 38 -> "10"
                resultado >= 36 -> "9.5"
                resultado >= 34 -> "9"
                resultado >= 32 -> "8.5"
                resultado >= 30 -> "8"
                resultado >= 28 -> "7.5"
                resultado >= 26 -> "7"
                resultado >= 24 -> "6.5"
                resultado >= 22 -> "6"
                resultado >= 20 -> "5.5"
                resultado >= 18 -> "5"
                resultado >= 16 -> "4.5"
                resultado >= 14 -> "4"
                resultado >= 12 -> "3.5"
                resultado >= 10 -> "3"
                resultado >= 8 -> "2.5"
                resultado >= 6 -> "2"
                else -> "N/A"
            }

            else -> "N/A"
        }

        else -> "N/A"
    }
}

fun calcularNotaFlexibilidad(resultado: Float, datosUsuario: DatosUsuario): String {
    return when (datosUsuario.genero) {
        "Hombre" -> when {
            datosUsuario.edad in 12..16 -> when (datosUsuario.edad) {
                12 -> when {
                    resultado >= 7 -> "10"
                    resultado >= 5 -> "9.5"
                    resultado >= 3 -> "9"
                    resultado >= 1 -> "8.5"
                    resultado >= 0 -> "8"
                    resultado >= -1 -> "7.5"
                    resultado >= -2 -> "7"
                    resultado >= -4 -> "6.5"
                    resultado >= -6 -> "6"
                    resultado >= -8 -> "5.5"
                    resultado >= -10 -> "5"
                    resultado >= -12 -> "4.5"
                    resultado >= -14 -> "4"
                    resultado >= -16 -> "3.5"
                    resultado >= -18 -> "3"
                    resultado >= -20 -> "2.5"
                    else -> "2"
                }
                13 -> when {
                    resultado >= 12 -> "10"
                    resultado >= 10 -> "9.5"
                    resultado >= 8 -> "9"
                    resultado >= 6 -> "8.5"
                    resultado >= 4 -> "8"
                    resultado >= 3 -> "7.5"
                    resultado >= 2 -> "7"
                    resultado >= 1 -> "6.5"
                    resultado >= 0 -> "6"
                    resultado >= -1 -> "5.5"
                    resultado >= -2 -> "5"
                    resultado >= -4 -> "4.5"
                    resultado >= -6 -> "4"
                    resultado >= -8 -> "3.5"
                    resultado >= -10 -> "3"
                    resultado >= -12 -> "2.5"
                    else -> "2"
                }
                14, 15, 16 -> when {
                    resultado >= 15 -> "10"
                    resultado >= 13 -> "9.5"
                    resultado >= 11 -> "9"
                    resultado >= 9 -> "8.5"
                    resultado >= 7 -> "8"
                    resultado >= 5 -> "7.5"
                    resultado >= 4 -> "7"
                    resultado >= 3 -> "6.5"
                    resultado >= 2 -> "6"
                    resultado >= 1 -> "5.5"
                    resultado >= 0 -> "5"
                    resultado >= -2 -> "4.5"
                    resultado >= -4 -> "4"
                    resultado >= -6 -> "3.5"
                    resultado >= -7 -> "3"
                    resultado >= -8 -> "2.5"
                    else -> "2"
                }
                else -> "N/A"
            }

            datosUsuario.edad >= 17 -> when {
                resultado >= 17 -> "10"
                resultado >= 15 -> "9.5"
                resultado >= 13 -> "9"
                resultado >= 11 -> "8.5"
                resultado >= 9 -> "8"
                resultado >= 7 -> "7.5"
                resultado >= 5 -> "7"
                resultado >= 3 -> "6.5"
                resultado >= 2 -> "6"
                resultado >= 1 -> "5.5"
                resultado >= 0 -> "5"
                resultado >= -2 -> "4.5"
                resultado >= -4 -> "4"
                resultado >= -6 -> "3.5"
                resultado >= -7 -> "3"
                resultado >= -8 -> "2.5"
                else -> "2"
            }

            else -> "N/A"
        }

        "Mujer" -> when {
            datosUsuario.edad in 12..16 -> when (datosUsuario.edad) {
                12 -> when {
                    resultado >= 7 -> "10"
                    resultado >= 5 -> "9.5"
                    resultado >= 3 -> "9"
                    resultado >= 1 -> "8.5"
                    resultado >= 0 -> "8"
                    resultado >= -1 -> "7.5"
                    resultado >= -2 -> "7"
                    resultado >= -4 -> "6.5"
                    resultado >= -6 -> "6"
                    resultado >= -8 -> "5.5"
                    resultado >= -10 -> "5"
                    resultado >= -12 -> "4.5"
                    resultado >= -14 -> "4"
                    resultado >= -16 -> "3.5"
                    resultado >= -18 -> "3"
                    resultado >= -20 -> "2.5"
                    else -> "2"
                }
                13 -> when {
                    resultado >= 12 -> "10"
                    resultado >= 10 -> "9.5"
                    resultado >= 8 -> "9"
                    resultado >= 6 -> "8.5"
                    resultado >= 4 -> "8"
                    resultado >= 3 -> "7.5"
                    resultado >= 2 -> "7"
                    resultado >= 1 -> "6.5"
                    resultado >= 0 -> "6"
                    resultado >= -1 -> "5.5"
                    resultado >= -2 -> "5"
                    resultado >= -4 -> "4.5"
                    resultado >= -6 -> "4"
                    resultado >= -8 -> "3.5"
                    resultado >= -10 -> "3"
                    resultado >= -12 -> "2.5"
                    else -> "2"
                }
                14, 15, 16 -> when {
                    resultado >= 17 -> "10"
                    resultado >= 15 -> "9.5"
                    resultado >= 13 -> "9"
                    resultado >= 11 -> "8.5"
                    resultado >= 9 -> "8"
                    resultado >= 7 -> "7.5"
                    resultado >= 5 -> "7"
                    resultado >= 4 -> "6.5"
                    resultado >= 3 -> "6"
                    resultado >= 2 -> "5.5"
                    resultado >= 1 -> "5"
                    resultado >= 0 -> "4.5"
                    resultado >= -2 -> "4"
                    resultado >= -4 -> "3.5"
                    resultado >= -6 -> "3"
                    resultado >= -7 -> "2.5"
                    else -> "2"
                }
                else -> "N/A"
            }

            datosUsuario.edad >= 17 -> when {
                resultado >= 19 -> "10"
                resultado >= 17 -> "9.5"
                resultado >= 15 -> "9"
                resultado >= 13 -> "8.5"
                resultado >= 11 -> "8"
                resultado >= 9 -> "7.5"
                resultado >= 7 -> "7"
                resultado >= 5 -> "6.5"
                resultado >= 3 -> "6"
                resultado >= 2 -> "5.5"
                resultado >= 1 -> "5"
                resultado >= 0 -> "4.5"
                resultado >= -2 -> "4"
                resultado >= -4 -> "3.5"
                resultado >= -6 -> "3"
                resultado >= -7 -> "2.5"
                else -> "2"
            }

            else -> "N/A"
        }
        else -> "N/A"
    }
}


fun calcularNotaTestCooper(resultado: Float, datosUsuario: DatosUsuario): String {
    return when (datosUsuario.genero) {
        "Hombre" -> when (datosUsuario.edad) {
            12 -> when {
                resultado >= 2600 -> "10"
                resultado >= 2400 -> "9.5"
                resultado >= 2200 -> "9"
                resultado >= 2100 -> "8.5"
                resultado >= 2000 -> "8"
                resultado >= 1950 -> "7.5"
                resultado >= 1900 -> "7"
                resultado >= 1850 -> "6.5"
                resultado >= 1800 -> "6"
                resultado >= 1750 -> "5.5"
                resultado >= 1700 -> "5"
                resultado >= 1650 -> "4.5"
                resultado >= 1600 -> "4"
                resultado >= 1550 -> "3.5"
                resultado >= 1500 -> "3"
                resultado >= 1450 -> "2.5"
                resultado >= 1400 -> "2"
                else -> "N/A"
            }

            13 -> when {
                resultado >= 2650 -> "10"
                resultado >= 2475 -> "9.5"
                resultado >= 2300 -> "9"
                resultado >= 2200 -> "8.5"
                resultado >= 2100 -> "8"
                resultado >= 2050 -> "7.5"
                resultado >= 2000 -> "7"
                resultado >= 1950 -> "6.5"
                resultado >= 1900 -> "6"
                resultado >= 1850 -> "5.5"
                resultado >= 1800 -> "5"
                resultado >= 1750 -> "4.5"
                resultado >= 1700 -> "4"
                resultado >= 1650 -> "3.5"
                resultado >= 1600 -> "3"
                resultado >= 1550 -> "2.5"
                resultado >= 1500 -> "2"
                else -> "N/A"
            }

            14 -> when {
                resultado >= 2650 -> "10"
                resultado >= 2525 -> "9.5"
                resultado >= 2400 -> "9"
                resultado >= 2300 -> "8.5"
                resultado >= 2200 -> "8"
                resultado >= 2150 -> "7.5"
                resultado >= 2100 -> "7"
                resultado >= 2050 -> "6.5"
                resultado >= 2000 -> "6"
                resultado >= 1950 -> "5.5"
                resultado >= 1900 -> "5"
                resultado >= 1850 -> "4.5"
                resultado >= 1800 -> "4"
                resultado >= 1750 -> "3.5"
                resultado >= 1700 -> "3"
                resultado >= 1650 -> "2.5"
                resultado >= 1600 -> "2"
                else -> "N/A"
            }

            15 -> when {
                resultado >= 2750 -> "10"
                resultado >= 2625 -> "9.5"
                resultado >= 2500 -> "9"
                resultado >= 2400 -> "8.5"
                resultado >= 2300 -> "8"
                resultado >= 2250 -> "7.5"
                resultado >= 2200 -> "7"
                resultado >= 2150 -> "6.5"
                resultado >= 2100 -> "6"
                resultado >= 2050 -> "5.5"
                resultado >= 2000 -> "5"
                resultado >= 1950 -> "4.5"
                resultado >= 1900 -> "4"
                resultado >= 1850 -> "3.5"
                resultado >= 1800 -> "3"
                resultado >= 1750 -> "2.5"
                resultado >= 1700 -> "2"
                else -> "N/A"
            }

            16 -> when {
                resultado >= 2800 -> "10"
                resultado >= 2650 -> "9.5"
                resultado >= 2500 -> "9"
                resultado >= 2450 -> "8.5"
                resultado >= 2400 -> "8"
                resultado >= 2350 -> "7.5"
                resultado >= 2300 -> "7"
                resultado >= 2250 -> "6.5"
                resultado >= 2200 -> "6"
                resultado >= 2150 -> "5.5"
                resultado >= 2100 -> "5"
                resultado >= 2050 -> "4.5"
                resultado >= 2000 -> "4"
                resultado >= 1950 -> "3.5"
                resultado >= 1900 -> "3"
                resultado >= 1850 -> "2.5"
                resultado >= 1800 -> "2"
                else -> "N/A"
            }

            in 17..99 -> when {
                resultado >= 17 -> "10"
                resultado >= 15 -> "9.5"
                resultado >= 13 -> "9"
                resultado >= 11 -> "8.5"
                resultado >= 9 -> "8"
                resultado >= 7 -> "7.5"
                resultado >= 5 -> "7"
                resultado >= 3 -> "6.5"
                resultado >= 2 -> "6"
                resultado >= 1 -> "5.5"
                resultado >= 0 -> "5"
                resultado >= -2 -> "4.5"
                resultado >= -4 -> "4"
                resultado >= -6 -> "3.5"
                resultado >= -7 -> "3"
                resultado >= -8 -> "2.5"
                else -> "2"
            }
            else -> "N/A"
        }



        "Mujer" -> when (datosUsuario.edad) {
            12 -> when {
                resultado >= 2200 -> "10"
                resultado >= 2100 -> "9.5"
                resultado >= 2000 -> "9"
                resultado >= 1900 -> "8.5"
                resultado >= 1800 -> "8"
                resultado >= 1750 -> "7.5"
                resultado >= 1700 -> "7"
                resultado >= 1550 -> "6.5"
                resultado >= 1600 -> "6"
                resultado >= 1550 -> "5.5"
                resultado >= 1500 -> "5"
                resultado >= 1450 -> "4.5"
                resultado >= 1400 -> "4"
                resultado >= 1350 -> "3.5"
                resultado >= 1300 -> "3"
                resultado >= 1250 -> "2.5"
                resultado >= 1200 -> "2"
                else -> "N/A"
            }
            13 -> when {
                resultado >= 2100 -> "10"
                resultado >= 2050 -> "9.5"
                resultado >= 2000 -> "9"
                resultado >= 1950 -> "8.5"
                resultado >= 1900 -> "8"
                resultado >= 1850 -> "7.5"
                resultado >= 1800 -> "7"
                resultado >= 1750 -> "6.5"
                resultado >= 1700 -> "6"
                resultado >= 1650 -> "5.5"
                resultado >= 1600 -> "5"
                resultado >= 1550 -> "4.5"
                resultado >= 1500 -> "4"
                resultado >= 1450 -> "3.5"
                resultado >= 1400 -> "3"
                resultado >= 1350 -> "2.5"
                resultado >= 1300 -> "2"
                else -> "N/A"
            }
            14 -> when {
                resultado >= 2200 -> "10"
                resultado >= 2050 -> "9.5"
                resultado >= 1900 -> "9"
                resultado >= 1850 -> "8.5"
                resultado >= 1800 -> "8"
                resultado >= 1750 -> "7.5"
                resultado >= 1700 -> "7"
                resultado >= 1650 -> "6.5"
                resultado >= 1600 -> "6"
                resultado >= 1550 -> "5.5"
                resultado >= 1500 -> "5"
                resultado >= 1450 -> "4.5"
                resultado >= 1400 -> "4"
                resultado >= 1350 -> "3.5"
                resultado >= 1300 -> "3"
                resultado >= 1250 -> "2.5"
                resultado >= 1200 -> "2"
                else -> "N/A"
            }
            15 -> when {
                resultado >= 2250 -> "10"
                resultado >= 2150 -> "9.5"
                resultado >= 2050 -> "9"
                resultado >= 1975 -> "8.5"
                resultado >= 1900 -> "8"
                resultado >= 1850 -> "7.5"
                resultado >= 1800 -> "7"
                resultado >= 1750 -> "6.5"
                resultado >= 1700 -> "6"
                resultado >= 1650 -> "5.5"
                resultado >= 1600 -> "5"
                resultado >= 1550 -> "4.5"
                resultado >= 1500 -> "4"
                resultado >= 1450 -> "3.5"
                resultado >= 1400 -> "3"
                resultado >= 1350 -> "2.5"
                resultado >= 1300 -> "2"
                else -> "N/A"
            }
            16 -> when {
                resultado >= 2300 -> "10"
                resultado >= 2200 -> "9.5"
                resultado >= 2100 -> "9"
                resultado >= 2025 -> "8.5"
                resultado >= 1950 -> "8"
                resultado >= 1900 -> "7.5"
                resultado >= 1850 -> "7"
                resultado >= 1800 -> "6.5"
                resultado >= 1750 -> "6"
                resultado >= 1700 -> "5.5"
                resultado >= 1650 -> "5"
                resultado >= 1600 -> "4.5"
                resultado >= 1550 -> "4"
                resultado >= 1500 -> "3.5"
                resultado >= 1450 -> "3"
                resultado >= 1400 -> "2.5"
                resultado >= 1350 -> "2"
                else -> "N/A"
            }
            in 17..99 -> when {
                resultado >= 17 -> "10"
                resultado >= 15 -> "9.5"
                resultado >= 13 -> "9"
                resultado >= 11 -> "8.5"
                resultado >= 9 -> "8"
                resultado >= 7 -> "7.5"
                resultado >= 5 -> "7"
                resultado >= 3 -> "6.5"
                resultado >= 2 -> "6"
                resultado >= 1 -> "5.5"
                resultado >= 0 -> "5"
                resultado >= -2 -> "4.5"
                resultado >= -4 -> "4"
                resultado >= -6 -> "3.5"
                resultado >= -7 -> "3"
                resultado >= -8 -> "2.5"
                else -> "2"
            }
            else -> "N/A"
        }
        else -> "N/A"
    }
}

fun calcularNotaVelocidad(resultado: Float, datosUsuario: DatosUsuario): String {
    return when (datosUsuario.genero) {
        "Hombre" -> when (datosUsuario.edad) {
            14 -> when {
                resultado >= 11 -> "10"
                resultado >= 12.6 -> "9.5"
                resultado >= 12.8 -> "9"
                resultado >= 13 -> "8.5"
                resultado >= 13.2 -> "8"
                resultado >= 13.4 -> "7.5"
                resultado >= 13.6 -> "7"
                resultado >= 13.8 -> "6.5"
                resultado >= 14 -> "6"
                resultado >= 14.1 -> "5.5"
                resultado >= 14.2 -> "5"
                resultado >= 14.4 -> "4.5"
                resultado >= 14.6 -> "4"
                resultado >= 15 -> "3.5"
                resultado >= 15.2 -> "3"
                resultado >= 15.4 -> "2.5"
                resultado >= 15.6 -> "2"
                else -> "N/A"
            }
            15 -> when {
                resultado >= 11 -> "10"
                resultado >= 12.3 -> "9.5"
                resultado >= 12.5 -> "9"
                resultado >= 12.7 -> "8.5"
                resultado >= 12.9 -> "8"
                resultado >= 13.1 -> "7.5"
                resultado >= 13.3 -> "7"
                resultado >= 13.5 -> "6.5"
                resultado >= 13.7 -> "6"
                resultado >= 13.9 -> "5.5"
                resultado >= 14.1 -> "5"
                resultado >= 14.3 -> "4.5"
                resultado >= 14.5 -> "4"
                resultado >= 14.7 -> "3.5"
                resultado >= 14.9 -> "3"
                resultado >= 15.1 -> "2.5"
                resultado >= 15.3 -> "2"
                else -> "N/A"
            }
            16 -> when {
                resultado >= 11 -> "10"
                resultado >= 12 -> "9.5"
                resultado >= 12.2 -> "9"
                resultado >= 12.4 -> "8.5"
                resultado >= 12.6 -> "8"
                resultado >= 12.8 -> "7.5"
                resultado >= 13 -> "7"
                resultado >= 13.2 -> "6.5"
                resultado >= 13.4 -> "6"
                resultado >= 13.6 -> "5.5"
                resultado >= 13.8 -> "5"
                resultado >= 14 -> "4.5"
                resultado >= 14.2 -> "4"
                resultado >= 14.4 -> "3.5"
                resultado >= 14.6 -> "3"
                resultado >= 14.8 -> "2.5"
                resultado >= 15 -> "2"
                else -> "N/A"
            }
            in 17..99 -> when {
                resultado >= 17 -> "10"
                resultado >= 15 -> "9.5"
                resultado >= 13 -> "9"
                resultado >= 11 -> "8.5"
                resultado >= 9 -> "8"
                resultado >= 7 -> "7.5"
                resultado >= 5 -> "7"
                resultado >= 3 -> "6.5"
                resultado >= 2 -> "6"
                resultado >= 1 -> "5.5"
                resultado >= 0 -> "5"
                resultado >= -2 -> "4.5"
                resultado >= -4 -> "4"
                resultado >= -6 -> "3.5"
                resultado >= -7 -> "3"
                resultado >= -8 -> "2.5"
                else -> "2"
            }
            else -> "N/A"
        }

        "Mujer" -> when (datosUsuario.edad) {
            14 -> when {
                resultado >= 11 -> "10"
                resultado >= 13.4 -> "9.5"
                resultado >= 13.6 -> "9"
                resultado >= 13.8 -> "8.5"
                resultado >= 14 -> "8"
                resultado >= 14.2 -> "7.5"
                resultado >= 14.4 -> "7"
                resultado >= 14.6 -> "6.5"
                resultado >= 14.8 -> "6"
                resultado >= 15 -> "5.5"
                resultado >= 15.2 -> "5"
                resultado >= 15.4 -> "4.5"
                resultado >= 15.6 -> "4"
                resultado >= 15.8 -> "3.5"
                resultado >= 16 -> "3"
                resultado >= 16.2 -> "2.5"
                resultado >= 16.4 -> "2"
                else -> "N/A"
            }
            15 -> when {
                resultado >= 11 -> "10"
                resultado >= 13.1 -> "9.5"
                resultado >= 13.3 -> "9"
                resultado >= 13.5 -> "8.5"
                resultado >= 13.7 -> "8"
                resultado >= 13.9 -> "7.5"
                resultado >= 14.1 -> "7"
                resultado >= 14.3 -> "6.5"
                resultado >= 14.5 -> "6"
                resultado >= 14.7 -> "5.5"
                resultado >= 14.9 -> "5"
                resultado >= 15.1 -> "4.5"
                resultado >= 15.3 -> "4"
                resultado >= 15.5 -> "3.5"
                resultado >= 15.7 -> "3"
                resultado >= 15.9 -> "2.5"
                resultado >= 16.1 -> "2"
                else -> "N/A"
            }
            16 -> when {
                resultado >= 11 -> "10"
                resultado >= 12.8 -> "9.5"
                resultado >= 13 -> "9"
                resultado >= 13.2 -> "8.5"
                resultado >= 13.4 -> "8"
                resultado >= 13.6 -> "7.5"
                resultado >= 13.8 -> "7"
                resultado >= 14 -> "6.5"
                resultado >= 14.2 -> "6"
                resultado >= 14.4 -> "5.5"
                resultado >= 14.6 -> "5"
                resultado >= 14.8 -> "4.5"
                resultado >= 15 -> "4"
                resultado >= 15.2 -> "3.5"
                resultado >= 15.4 -> "3"
                resultado >= 15.6 -> "2.5"
                resultado >= 18.8 -> "2"
                else -> "N/A"
            }
            in 17..99 -> when {
                resultado >= 17 -> "10"
                resultado >= 15 -> "9.5"
                resultado >= 13 -> "9"
                resultado >= 11 -> "8.5"
                resultado >= 9 -> "8"
                resultado >= 7 -> "7.5"
                resultado >= 5 -> "7"
                resultado >= 3 -> "6.5"
                resultado >= 2 -> "6"
                resultado >= 1 -> "5.5"
                resultado >= 0 -> "5"
                resultado >= -2 -> "4.5"
                resultado >= -4 -> "4"
                resultado >= -6 -> "3.5"
                resultado >= -7 -> "3"
                resultado >= -8 -> "2.5"
                else -> "2"
            }
            else -> "N/A"
        }
        else -> "N/A"
    }
}

fun calcularNotaLanzamiento(resultado: Float, datosUsuario: DatosUsuario): String {
    return when (datosUsuario.genero) {
        "Hombre" -> when (datosUsuario.edad) {
            15 -> when {
                resultado >= 8.50 -> "10"
                resultado >= 8.20 -> "9.5"
                resultado >= 7.90 -> "9"
                resultado >= 7.60 -> "8.5"
                resultado >= 7.30 -> "8"
                resultado >= 7.00 -> "7.5"
                resultado >= 6.70 -> "7"
                resultado >= 6.40 -> "6.5"
                resultado >= 6.10 -> "6"
                resultado >= 5.80 -> "5.5"
                resultado >= 5.50 -> "5"
                resultado >= 5.20 -> "4.5"
                resultado >= 5.00 -> "4"
                resultado >= 4.70 -> "3.5"
                resultado >= 4.40 -> "3"
                resultado >= 4.10 -> "2.5"
                resultado >= 3.80 -> "2"
                else -> "N/A"
            }
            16 -> when {
                resultado >= 8.70 -> "10"
                resultado >= 8.40 -> "9.5"
                resultado >= 8.10 -> "9"
                resultado >= 7.80 -> "8.5"
                resultado >= 7.50 -> "8"
                resultado >= 7.20 -> "7.5"
                resultado >= 6.90 -> "7"
                resultado >= 6.60 -> "6.5"
                resultado >= 6.30 -> "6"
                resultado >= 6.10 -> "5.5"
                resultado >= 5.90 -> "5"
                resultado >= 5.70 -> "4.5"
                resultado >= 5.50 -> "4"
                resultado >= 5.30 -> "3.5"
                resultado >= 5.10 -> "3"
                resultado >= 4.90 -> "2.5"
                resultado >= 4.70 -> "2"
                else -> "N/A"
            }
            in 17..99 -> when {
                resultado >= 17 -> "10"
                resultado >= 15 -> "9.5"
                resultado >= 13 -> "9"
                resultado >= 11 -> "8.5"
                resultado >= 9 -> "8"
                resultado >= 7 -> "7.5"
                resultado >= 5 -> "7"
                resultado >= 3 -> "6.5"
                resultado >= 2 -> "6"
                resultado >= 1 -> "5.5"
                resultado >= 0 -> "5"
                resultado >= -2 -> "4.5"
                resultado >= -4 -> "4"
                resultado >= -6 -> "3.5"
                resultado >= -7 -> "3"
                resultado >= -8 -> "2.5"
                else -> "2"
            }
            else -> "N/A"
        }

        "Mujer" -> when (datosUsuario.edad) {
            15 -> when {
                resultado >= 8.50 -> "10"
                resultado >= 8.20 -> "9.5"
                resultado >= 7.90 -> "9"
                resultado >= 7.60 -> "8.5"
                resultado >= 7.30 -> "8"
                resultado >= 7.00 -> "7.5"
                resultado >= 6.70 -> "7"
                resultado >= 6.40 -> "6.5"
                resultado >= 6.10 -> "6"
                resultado >= 5.80 -> "5.5"
                resultado >= 5.50 -> "5"
                resultado >= 5.20 -> "4.5"
                resultado >= 5.00 -> "4"
                resultado >= 4.70 -> "3.5"
                resultado >= 4.40 -> "3"
                resultado >= 4.10 -> "2.5"
                resultado >= 3.80 -> "2"
                else -> "N/A"
            }
            16 -> when {
                resultado >= 8.70 -> "10"
                resultado >= 8.40 -> "9.5"
                resultado >= 8.10 -> "9"
                resultado >= 7.80 -> "8.5"
                resultado >= 7.50 -> "8"
                resultado >= 7.20 -> "7.5"
                resultado >= 6.90 -> "7"
                resultado >= 6.60 -> "6.5"
                resultado >= 6.30 -> "6"
                resultado >= 6.10 -> "5.5"
                resultado >= 5.90 -> "5"
                resultado >= 5.70 -> "4.5"
                resultado >= 5.50 -> "4"
                resultado >= 5.30 -> "3.5"
                resultado >= 5.10 -> "3"
                resultado >= 4.90 -> "2.5"
                resultado >= 4.70 -> "2"
                else -> "N/A"
            }
            in 17..99 -> when {
                resultado >= 17 -> "10"
                resultado >= 15 -> "9.5"
                resultado >= 13 -> "9"
                resultado >= 11 -> "8.5"
                resultado >= 9 -> "8"
                resultado >= 7 -> "7.5"
                resultado >= 5 -> "7"
                resultado >= 3 -> "6.5"
                resultado >= 2 -> "6"
                resultado >= 1 -> "5.5"
                resultado >= 0 -> "5"
                resultado >= -2 -> "4.5"
                resultado >= -4 -> "4"
                resultado >= -6 -> "3.5"
                resultado >= -7 -> "3"
                resultado >= -8 -> "2.5"
                else -> "2"
            }
            else -> "N/A"
        }
        else -> "N/A"
    }
}
