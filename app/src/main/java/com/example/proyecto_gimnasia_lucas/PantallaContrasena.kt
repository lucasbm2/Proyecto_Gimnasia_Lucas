package com.example.proyecto_gimnasia_lucas

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_gimnasia_lucas.database.LoginDBHelper

//Función para la pantalla de contraseña
@Composable
fun PantallaContrasena( navigateToBack: () -> Unit) {
    val context = LocalContext.current
    val loginHelper = LoginDBHelper(context)

    var usuario by rememberSaveable { mutableStateOf("") }
    var nuevaContrasena by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement =  Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Cambiar Contraseña", fontSize = 25.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Usuario:", fontSize = 20.sp)
        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            placeholder = { Text("Introduce usuario") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Nueva Contraseña:", fontSize = 20.sp)
        TextField(
            value = nuevaContrasena,
            onValueChange = { nuevaContrasena = it },
            placeholder = { Text("Introduce nueva contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Boton para actualizar la contraseña
        //Si el el campo no está vacio, si el usuario existe y no es nulo, se actualiza la contraseña
        Button(onClick = {
            if (usuario.isNotEmpty() && nuevaContrasena.isNotEmpty()) {
                val usuarioExiste = loginHelper.getNombreUsuarioPorNombre(usuario)
                if (usuarioExiste != null) {
                    val actualizado = loginHelper.actualizarUsuario(usuario, nuevaContrasena)
                    if (actualizado) {
                        Toast.makeText(context, "Contraseña actualizada", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Error al actualizar", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "Actualizar Contraseña")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navigateToBack() }) {
            Text(text = "Volver")
        }

    }
}
