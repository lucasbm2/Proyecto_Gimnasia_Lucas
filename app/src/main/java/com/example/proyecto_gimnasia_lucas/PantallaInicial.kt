package com.example.proyecto_gimnasia_lucas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaInicial(navigateToLogin: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.imagen_gimnasio),
            contentDescription = "Gimnasia",
            modifier = Modifier.fillMaxSize()
                .clickable { navigateToLogin() },
            contentScale = ContentScale.Crop,
        )

        Text(
            text = "GIMNASIAPP",
            fontSize = 40.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 50.dp)
        )

        Text(
            text = "Pulsa en la imagen para acceder",
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun PantallaInicialPreview() {
    PantallaInicial(navigateToLogin = {})
}