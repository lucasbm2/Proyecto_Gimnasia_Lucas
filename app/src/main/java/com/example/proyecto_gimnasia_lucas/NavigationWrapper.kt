package com.example.proyecto_gimnasia_lucas


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlin.reflect.typeOf


//funciona navegacion entre pantallas
@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            PantallaLogin { navController.navigate(MenuPrincipal) }
        }
    }
}
