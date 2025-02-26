package com.example.proyecto_gimnasia_lucas


import android.os.Parcelable
import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Switch
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_gimnasia_lucas.database.DatosDBHelper
import com.example.proyecto_gimnasia_lucas.database.LoginDBHelper
import kotlinx.parcelize.Parcelize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp



@Composable
fun PantallaPrincipal(
    nombreUsuario: String,
    navigateToNotas: () -> Unit,
    navigateToPruebas: (DatosUsuario) -> Unit,
    navigateToCalculoIMC: (DatosUsuario) -> Unit,
    onThemeToggle: () -> Unit
) {
    var isDarkMode by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val datosHelper = DatosDBHelper(context)
    val loginHelper = LoginDBHelper(context)

    var genero by rememberSaveable { mutableStateOf("") }
    var peso by rememberSaveable { mutableStateOf("") }
    var edad by rememberSaveable { mutableStateOf("") }
    var altura by rememberSaveable { mutableStateOf("") }
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var idUsuario by remember { mutableStateOf<Int?>(null) }

    // Función para guardar los datos en la base de datos antes de navegar
    fun guardarDatos() {
        val datosUsuario = DatosUsuario(
            peso = peso.toIntOrNull() ?: 0,
            edad = edad.toIntOrNull() ?: 0,
            altura = altura.toIntOrNull() ?: 0,
            genero = genero
        )

        idUsuario?.let { usuarioId ->
            datosHelper.insertarOActualizarDatos(
                usuarioId,
                datosUsuario.edad,
                datosUsuario.peso,
                datosUsuario.altura,
                datosUsuario.genero == "Hombre"
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Modo oscuro", color = MaterialTheme.colorScheme.onBackground)
            Switch(
                checked = isDarkMode,
                onCheckedChange = {
                    isDarkMode = it
                    onThemeToggle()
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button(onClick = {
                val datosUsuario = DatosUsuario(
                    peso = peso.toIntOrNull() ?: 0,
                    edad = edad.toIntOrNull() ?: 0,
                    altura = altura.toIntOrNull() ?: 0,
                    genero = genero
                )
                guardarDatos()
                navigateToPruebas(datosUsuario)
            }, modifier = Modifier.size(width = 150.dp, height = 50.dp)) {
                Text("Ir a Pruebas")
            }

            Spacer(modifier = Modifier.width(80.dp))

        }

        Spacer(modifier = Modifier.weight(0.8f))

        Text(
            text = "INTRODUCE TUS DATOS",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(10.dp),
            lineHeight = 40.sp
        )

        Spacer(modifier = Modifier.weight(0.2f))

        Text(text = "Peso", fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground)
        TextField(
            value = peso,
            onValueChange = { peso = it },
            placeholder = { Text("Introduce tu peso") }
        )

        Spacer(modifier = Modifier.weight(0.8f))

        Text(text = "Edad", fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground)
        TextField(
            value = edad,
            onValueChange = { edad = it },
            placeholder = { Text("Introduce tu edad") }
        )

        Spacer(modifier = Modifier.weight(0.8f))

        Text(text = "Altura", fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground)
        TextField(
            value = altura,
            onValueChange = { altura = it },
            placeholder = { Text("Introduce tu altura (cm)") }
        )

        Spacer(modifier = Modifier.weight(0.4f))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                RadioButton(selected = (genero == "Hombre"), onClick = { genero = "Hombre" })
                Text(text = "Hombre", color = MaterialTheme.colorScheme.onBackground)
                RadioButton(selected = (genero == "Mujer"), onClick = { genero = "Mujer" })
                Text(text = "Mujer", color = MaterialTheme.colorScheme.onBackground)
            }
        }

        Spacer(modifier = Modifier.weight(0.4f))

        Button(onClick = {
            showDialog = true
            guardarDatos()
        }, modifier = Modifier.size(width = 250.dp, height = 100.dp)) {
            Text(text = "Calcular IMC", fontSize = 25.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
    }

    MyDialog(show = showDialog, onDismiss = { showDialog = false }, onConfirm = {
        guardarDatos()
        navigateToCalculoIMC(
            DatosUsuario(
                peso = peso.toInt(),
                edad = edad.toInt(),
                altura = altura.toInt(),
                genero = genero
            )
        )
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