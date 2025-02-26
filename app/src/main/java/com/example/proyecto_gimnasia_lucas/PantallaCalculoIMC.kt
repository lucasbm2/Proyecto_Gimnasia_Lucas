package com.example.proyecto_gimnasia_lucas


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cálculo de IMC",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3F51B5),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Text(
            text = "IMC calculado: %.2f".format(imc),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3F51B5),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.padding(16.dp))

        val mensaje = when {
            datosUsuario.genero == "Hombre" -> when (imc) {
                in 0.0..18.4 -> "Hay que comer más"
                in 18.5..24.9 -> "De categoría"
                in 25.0..29.9 -> "Recorta la cerveza de después de trabajar"
                in 30.0..150.0 -> "Cuidado ahí, salud primero"
                else -> "IMC fuera de rango"
            }
            datosUsuario.genero == "Mujer" -> when (imc) {
                in 0.0..18.4 -> "Come más cosas"
                in 18.5..24.9 -> "Te mantienes perfectamente"
                in 25.0..29.9 -> "Recorta los dulces"
                in 30.0..150.0 -> "Cuidado de verdad"
                else -> "IMC fuera de rango"
            }
            else -> "Datos de género no reconocidos"
        }

        Text(
            text = mensaje,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3F51B5),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Button(
            onClick = { navigateToBack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3F51B5),
                contentColor = Color.White
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

