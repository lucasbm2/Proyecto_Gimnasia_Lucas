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

        composable("PantallaCalculoIMC") {
            val datosUsuario = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<DatosUsuario>("datosUsuario")

            PantallaCalculoIMC(
                navigateToBack = { navController.popBackStack() },
                datosUsuario = datosUsuario ?: DatosUsuario(0, 0, 0, "Sin género")
            )
        }


        composable("PantallaPrincipal") {
            PantallaPrincipal(
                navigateToNotas = { navController.navigate("PantallaNotas") },
                navigateToPruebas = { datosUsuario ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("datosUsuario", datosUsuario)
                    navController.navigate("PruebaRV")
                },
                navigateToCalculoIMC = { datosUsuario ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("datosUsuario", datosUsuario)
                    navController.navigate("PantallaCalculoIMC")
                }
            )
        }

        composable("PruebaRV") {
            val datosUsuario = navController.previousBackStackEntry?.savedStateHandle?.get<DatosUsuario>("datosUsuario")

            PruebasList(
                datosUsuario = datosUsuario ?: DatosUsuario(0, 0, 0, "Sin género"),
                onItemClick = { prueba, datosUsuario ->
                    println("Guardando datos antes de navegar a PantallaMarcas: $datosUsuario")


                    navController.currentBackStackEntry?.savedStateHandle?.set("datosUsuario", datosUsuario)
                    navController.navigate("PantallaMarcas")
                },
                navigateToBack = { navController.popBackStack() }
            )
        }

        composable("PantallaMarcas") {
            val datosUsuario = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<DatosUsuario>("datosUsuario")

            MostrarDatos(
                datosUsuario = datosUsuario,
                navigateToBack = { navController.popBackStack() }
            )
        }


    }

}