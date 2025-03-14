package com.example.proyecto_gimnasia_lucas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Funcion para la pantalla de calculo de IMC
@Composable
fun PantallaCalculoIMC(navigateToBack: () -> Unit, datosUsuario: DatosUsuario) {
    val imc = if (datosUsuario.altura > 0) {
        datosUsuario.peso / ((datosUsuario.altura / 100.0) * (datosUsuario.altura / 100.0))
    } else {
        0.0
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cálculo de IMC",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Text(
            //Coger solo 2 decimales
            text = "IMC calculado: %.2f".format(imc),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.padding(16.dp))

        //Mensajes a mostrar conforme los resultados
        val mensaje = when {
            datosUsuario.genero == "Hombre" -> when (imc) {
                in 0.0..18.4 -> "Déficit de peso"
                in 18.5..24.9 -> "Intervalo normal"
                in 25.0..29.9 -> "Sobrepeso moderado"
                in 30.0..150.0 -> "Obesidad"
                else -> "IMC no disponible"
            }
            datosUsuario.genero == "Mujer" -> when (imc) {
                in 0.0..18.4 -> "Déficit de peso"
                in 18.5..24.9 -> "Intervalo normal"
                in 25.0..29.9 -> "Sobrepeso moderado"
                in 30.0..150.0 -> "Obesidad"
                else -> "IMC no disponible"
            }
            else -> "No es hombre ni mujer"
        }

        //Para mostrar el resultado
        Text(
            text = mensaje,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Button(
            onClick = { navigateToBack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Volver",
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
    }
}
