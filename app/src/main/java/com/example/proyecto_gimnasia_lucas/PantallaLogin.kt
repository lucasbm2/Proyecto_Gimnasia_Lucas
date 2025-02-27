package com.example.proyecto_gimnasia_lucas

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_gimnasia_lucas.database.EntLogin
import com.example.proyecto_gimnasia_lucas.database.LoginDBHelper

//Función para la pantalla de login
@Composable
fun PantallaLogin(
    navigateToPantallaPrincipal: (String) -> Unit = {},
    navigateToBack: () -> Unit,
    navigateToPantallaContrasena: () -> Unit
) {
    val context = LocalContext.current
    val loginHelper = LoginDBHelper(context = LocalContext.current)

    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(0.1f))
        Text(text = "Introduzca sus datos", fontSize = 25.sp)

        Spacer(modifier = Modifier.weight(0.1f))

        Text(text = "Usuario:", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))
        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            placeholder = { Text(text = "Introduce tu usuario") }
        )

        Spacer(modifier = Modifier.weight(0.1f))

        Text(text = "Contraseña:", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))
        TextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            placeholder = { Text(text = "Introduce tu contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )
        Text(
            text = "Cambiar contraseña",
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
                .clickable { navigateToPantallaContrasena()}
        )

        Spacer(modifier = Modifier.weight(0.1f))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { navigateToBack() }) {
                Text(text = "Volver atrás")
            }

            Button(onClick = {
                if (usuario.isEmpty()) {
                    Toast.makeText(context, "Introduzca un nombre de usuario", Toast.LENGTH_SHORT).show()
                } else if (contrasena.isEmpty()) {
                    Toast.makeText(context, "Introduzca una contraseña", Toast.LENGTH_SHORT).show()
                } else {

                    val nombreUsuarioEncontrado = loginHelper.getNombreUsuarioPorNombre(usuario)

                    if (nombreUsuarioEncontrado != null) {
                        val usuarioExiste = loginHelper.verificarUsuario(usuario, contrasena)
                        if (usuarioExiste) {
                            Toast.makeText(context, "Bienvenido $nombreUsuarioEncontrado", Toast.LENGTH_SHORT).show()

                            navigateToPantallaPrincipal(nombreUsuarioEncontrado)
                        } else {
                            Toast.makeText(context, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Usuario no encontrado, creando usuario...", Toast.LENGTH_SHORT).show()

                        val nuevoUsuario = EntLogin(usuario = usuario, password = contrasena)
                        val usuarioID = loginHelper.insertarUsuario(nuevoUsuario)

                        Toast.makeText(context, "Nuevo usuario insertado: $usuario", Toast.LENGTH_SHORT).show()

                        navigateToPantallaPrincipal(usuario)
                    }
                }
            }) {
                Text(text = "Entrar")
            }
        }


        Spacer(modifier = Modifier.weight(0.1f))
    }
}
