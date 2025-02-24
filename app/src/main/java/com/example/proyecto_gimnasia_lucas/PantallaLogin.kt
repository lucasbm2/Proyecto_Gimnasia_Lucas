package com.example.proyecto_gimnasia_lucas


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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PantallaLogin(
    navigateToPantallaPrincipal: () -> Unit = {},
    navigateToBack: () -> Unit) {
    
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

        Spacer(modifier = Modifier.weight(0.1f))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { navigateToBack() }) {
                Text(text = "Volver atrás")
            }
            Button(onClick = { navigateToPantallaPrincipal() }) {
                Text(text = "Entrar")
            }
        }

        Spacer(modifier = Modifier.weight(0.1f))
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaLoginPreview() {
    PantallaLogin( navigateToPantallaPrincipal = {}, navigateToBack = {})
}