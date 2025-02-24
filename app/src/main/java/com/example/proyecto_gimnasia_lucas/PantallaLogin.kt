package com.example.proyecto_gimnasia_lucas


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { PantallaLogin { navController.navigate("home") } }
        composable("home") { NavegarMenu { navController.navigate("MenuPrincipal") } }

    }
}


@Composable
fun PantallaLogin( navigateToHome: () ->Unit) {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (boxLogo, boxLogin, boxPassword, boxButton) = createRefs()
        val topGuide = createGuidelineFromTop(0.1f)
        Box(Modifier
            .fillMaxWidth()
            .height(125.dp)
            .constrainAs(boxLogo) {
                top.linkTo(topGuide)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Text(
                text = "Introduzca sus datos",
                Modifier
                    .align(Alignment.Center)
                    .size(250.dp),
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
            )
        }

        Box(Modifier
            .fillMaxWidth()
            .height(120.dp)
            .constrainAs(boxLogin) {
                top.linkTo(boxLogo.bottom)
            }) {
            var textoLogin by remember { mutableStateOf("") }
            Text(
                "Usuario: ", Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 10.dp)
            )

            TextField(
                value = textoLogin,
                onValueChange = { textoLogin = it },
                Modifier
                    .align(Alignment.Center)
                    .padding(top = 10.dp)
            )
        }

        Box(Modifier
            .fillMaxWidth()
            .height(120.dp)
            .constrainAs(boxPassword) {
                top.linkTo(boxLogin.bottom)
            }) {
            var textoPass by remember { mutableStateOf("") }
            Text(
                "Contraseña: ", Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 10.dp)
            )

            TextField(
                value = textoPass, // El texto actual en el campo
                onValueChange = { textoPass = it }, // Actualiza el texto cuando cambia
                Modifier
                    .align(Alignment.Center)
                    .padding(top = 10.dp),
                visualTransformation = PasswordVisualTransformation(mask = '*')
            )
        }

        Box(Modifier
            .fillMaxWidth()
            .height(375.dp)
            .constrainAs(boxButton) {
                top.linkTo(boxPassword.bottom)
            }) {

            Button(
                onClick = {
                    navigateToHome()
                },
                Modifier
                    .align(Alignment.Center)
                    .width(150.dp)
                    .height(50.dp)
            ) {
                Text("Enviar")
            }

        }
    }
}


@Composable
fun NavegarMenu(navigateToMenuPrincipal: (String) -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(text = " MENU PRINCIPAL ", fontSize = 25.sp)

        Spacer(modifier = Modifier.weight(1f))


        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun showLogin() {
    PantallaLogin( navigateToHome = { } )
}