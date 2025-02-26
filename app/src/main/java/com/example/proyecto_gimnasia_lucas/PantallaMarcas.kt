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

@Composable
fun MostrarDatos(datosUsuario: DatosUsuario?) {
    if (datosUsuario == null) {
        Text(text = "No se encontraron datos del usuario", color = Color.Red)
        println("datosUsuario es nulo")
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Datos del Usuario", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "Peso: ${datosUsuario.peso}")
            Text(text = "Edad: ${datosUsuario.edad}")
            Text(text = "Altura: ${datosUsuario.altura}")
            Text(text = "Género: ${datosUsuario.genero}")
        }
    }
}
