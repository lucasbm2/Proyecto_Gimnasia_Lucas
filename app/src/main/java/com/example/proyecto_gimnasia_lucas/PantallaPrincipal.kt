package com.example.proyecto_gimnasia_lucas


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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PantallaPrincipal(
    navigateToNotas: () -> Unit,
    navigateToPruebas: () -> Unit,
    navigateToCalculoIMC: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button(onClick = { navigateToPruebas() }) {
                Text(
                    text = "Pruebas",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(10.dp)
                )
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
            placeholder = { Text(text = "Introduce tu altura") })
        Spacer(modifier = Modifier.weight(0.4f))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                RadioButton(
                    selected = (text == "Hombre"), onClick = { text = "Hombre" }, enabled = true
                )
                Text(text = "Hombre")

                RadioButton(
                    selected = (text == "Mujer"), onClick = { text = "Mujer" }, enabled = true
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
        navigateToCalculoIMC()
        showDialog = false
    })
}

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