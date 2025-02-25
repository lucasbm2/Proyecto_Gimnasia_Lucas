package com.example.proyecto_gimnasia_lucas


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composecatalog.navigation.PantallaCalculoIMC
import com.example.composecatalog.navigation.PantallaInicial
import com.example.composecatalog.navigation.PantallaPrincipal
import com.example.composecatalog.navigation.PantallaLogin
import com.example.composecatalog.navigation.PantallaMarcas
import com.example.composecatalog.navigation.PantallaNotas
import com.example.composecatalog.navigation.PruebaRV
import com.example.proyecto_gimnasia_lucas.model.ItemPrueba
import com.example.proyecto_gimnasia_lucas.model.PruebasList


@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "PantallaInicial") {
        composable("PantallaInicial") {
            PantallaInicial { navController.navigate("PantallaLogin") }
        }

        composable("PantallaLogin") {
            PantallaLogin(
                navigateToPantallaPrincipal = { navController.navigate("PantallaPrincipal") },
                navigateToBack = { navController.popBackStack() }
            )
        }

        composable("PantallaPrincipal") {
            PantallaPrincipal(
                navigateToNotas = { navController.navigate("PantallaNotas") },
                navigateToPruebas = { datosUsuario ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("datosUsuario", datosUsuario)
                    navController.navigate("PruebaRV")
                },
                navigateToCalculoIMC = { navController.navigate("PantallaCalculoIMC") }
            )
        }

        composable("PruebaRV") { backStackEntry ->
            val datosUsuario = backStackEntry.savedStateHandle?.get<DatosUsuario>("datosUsuario")

            PruebasList(
                datosUsuario = datosUsuario ?: DatosUsuario(0, 0, 0, "Sin género"),
                onItemClick = { prueba, datosUsuario ->
                    println(" Guardando datos antes de navegar: $datosUsuario")


                    navController.currentBackStackEntry?.savedStateHandle?.set("datosUsuario", datosUsuario)



                    navController.navigate("PantallaMarcas")
                }

                ,
                navigateToBack = { navController.popBackStack() }
            )
        }

        composable("PantallaMarcas") { backStackEntry ->
            val datosUsuario = backStackEntry.savedStateHandle?.get<DatosUsuario>("datosUsuario")

            if (datosUsuario != null) {
                println("DatosUsuario recuperado en PantallaMarcas: $datosUsuario")
                MostrarDatos(datosUsuario)
            } else {
                println("No se encontraron datos del usuario.")
            }
        }
    }

}