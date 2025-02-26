package com.example.proyecto_gimnasia_lucas


import android.os.Parcelable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.parcelize.Parcelize
import java.io.Serializable



@Composable
fun PantallaPrincipal(
    navigateToNotas: () -> Unit,
    navigateToPruebas: ( DatosUsuario) -> Unit,
    navigateToCalculoIMC: (DatosUsuario) -> Unit
) {
    var genero by rememberSaveable { mutableStateOf("") }
    var peso by rememberSaveable { mutableStateOf("") }
    var edad by rememberSaveable { mutableStateOf("") }
    var altura by rememberSaveable { mutableStateOf("") }
    var showDialog by rememberSaveable { mutableStateOf(false) }


    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button(onClick = {
                val datosUsuario = DatosUsuario(
                    peso = peso.toIntOrNull() ?: 0,
                    edad = edad.toIntOrNull() ?: 0,
                    altura = altura.toIntOrNull() ?: 0,
                    genero = genero
                )

                println("📋 Enviando datos desde PantallaPrincipal: $datosUsuario")

                navigateToPruebas(datosUsuario)
            }) {
                Text("Ir a Pruebas")
            }

            Spacer(modifier = Modifier.width(80.dp))
            Button(onClick = { navigateToNotas() }) {
                Text(
                    text = "Notas",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(0.8f))
        Text(
            text = "INTRODUCE TUS DATOS",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp),
            lineHeight = 40.sp,
        )
        Spacer(modifier = Modifier.weight(0.2f))
        Text(text = "Peso", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))
        TextField(
            value = peso,
            onValueChange = { peso = it },
            placeholder = { Text(text = "Introduce tu peso") })
        Spacer(modifier = Modifier.weight(0.8f))
        Text(text = "Edad ", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))
        TextField(
            value = edad,
            onValueChange = { edad = it },
            placeholder = { Text(text = "Introduce tu edad") })
        Spacer(modifier = Modifier.weight(0.8f))
        Text(text = "Altura", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))
        TextField(
            value = altura,
            onValueChange = { altura = it },
            placeholder = { Text(text = "Introduce tu altura (en cm)") })
        Spacer(modifier = Modifier.weight(0.4f))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                RadioButton(
                    selected = (genero == "Hombre"), onClick = { genero = "Hombre" }, enabled = true
                )
                Text(text = "Hombre")

                RadioButton(
                    selected = (genero == "Mujer"), onClick = { genero = "Mujer" }, enabled = true
                )
                Text(text = "Mujer")
            }
        }

        Spacer(modifier = Modifier.weight(0.4f))

        Button(onClick = { showDialog = true }) {
            Text(
                text = "Calcular IMC",
                fontSize = 18.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }

    MyDialog(show = showDialog, onDismiss = { showDialog = false }, onConfirm = {
        navigateToCalculoIMC( DatosUsuario(peso = peso.toInt(), edad = edad.toInt(), altura = altura.toInt(), genero = genero) )
        showDialog = false
    })
}


@Parcelize
data class DatosUsuario(
    val peso: Int,
    val edad: Int,
    val altura: Int,
    val genero: String
) : Parcelable



@Composable
fun MyDialog(show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Cancelar")
                }
            },
            title = { Text(text = "Confirmar IMC") },
            text = { Text(text = "¿Estás seguro de que quieres calcular tu IMC con estos datos?") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPrincipalPreview() {
    PantallaPrincipal(
        navigateToNotas = {},
        navigateToPruebas = {},
        navigateToCalculoIMC = {},
    )
}