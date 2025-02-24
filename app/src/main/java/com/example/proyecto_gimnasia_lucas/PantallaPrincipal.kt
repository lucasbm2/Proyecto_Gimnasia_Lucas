package com.example.proyecto_gimnasia_lucas


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaPrincipal() {
    Column() {
        Image(
            painter = painterResource(id = R.drawable.imagen_gimnasio),
            contentDescription = "Imagen de ejemplo",
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp)
        )
    }
}



@Preview (showBackground = true)
@Composable
fun previewPantalla() {
    PantallaPrincipal()

}