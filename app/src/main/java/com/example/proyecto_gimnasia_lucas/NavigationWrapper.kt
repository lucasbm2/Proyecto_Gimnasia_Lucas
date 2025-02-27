package com.example.proyecto_gimnasia_lucas

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composecatalog.navigation.PantallaCalculoIMC
import com.example.composecatalog.navigation.PantallaContrasena
import com.example.composecatalog.navigation.PantallaInicial
import com.example.composecatalog.navigation.PantallaLogin
import com.example.composecatalog.navigation.PantallaMarcas
import com.example.composecatalog.navigation.PantallaNotas
import com.example.composecatalog.navigation.PantallaPrincipal
import com.example.composecatalog.navigation.PruebaRV
import com.example.proyecto_gimnasia_lucas.model.Prueba
import com.example.proyecto_gimnasia_lucas.model.PruebasList


//Funcion para navegar entre las pantallas
@Composable
fun NavigationWrapper(onThemeToggle: () -> Unit) {
    val navController = rememberNavController()

    //NavController es el controlador para navegar entre pantallas
    NavHost(navController = navController, startDestination = PantallaInicial) {

        composable<PantallaInicial> {
            PantallaInicial { navController.navigate(PantallaLogin) }
        }

        composable<PantallaLogin> {
            PantallaLogin(
                navigateToPantallaPrincipal = { navController.navigate(PantallaPrincipal) },
                navigateToBack = { navController.popBackStack() },
                navigateToPantallaContrasena = { navController.navigate(PantallaContrasena) }
            )
        }

        composable<PantallaCalculoIMC> {
            //Coge la entrada anterior de la pila de navegacion
            val datosUsuario = navController.previousBackStackEntry
                //Reucpera el objeto que se ha guardado anteriormente
                ?.savedStateHandle
                //Clave del objeto
                ?.get<DatosUsuario>("datosUsuario")

            PantallaCalculoIMC(
                navigateToBack = { navController.popBackStack() },
                datosUsuario = datosUsuario ?: DatosUsuario(0, 0, 0, "Sin género")
            )
        }

        composable<PantallaPrincipal> {
            val nombreUsuario = navController.previousBackStackEntry
                ?.savedStateHandle
                //Busca la clave del objeto que se ha guardado si no es nulo
                ?.get<String>("nombreUsuario") ?: "Desconocido"

            PantallaPrincipal(
                nombreUsuario = nombreUsuario,
                navigateToNotas = { navController.navigate(PantallaNotas) },
                navigateToPruebas = { datosUsuario ->
                    //Guarda el objeto en la pila de navegacion con la clave datosUsuario
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "datosUsuario",
                        datosUsuario
                    )
                    navController.navigate(PruebaRV)
                },
                navigateToCalculoIMC = { datosUsuario ->
                    //Guarda el objeto en la pila de navegacion con la clave datosUsuario
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "datosUsuario",
                        datosUsuario
                    )
                    navController.navigate(PantallaCalculoIMC)
                },
                onThemeToggle = onThemeToggle
            )
        }

        composable<PruebaRV> {
            val datosUsuario =
                //Busca el objeto que se ha guardado anteriormente y lo recupera
                navController.previousBackStackEntry?.savedStateHandle?.get<DatosUsuario>("datosUsuario")

            PruebasList(

                datosUsuario = datosUsuario ?: DatosUsuario(0, 0, 0, "Sin género"),
                //Al hacer click en un CARD se navega a la pantalla de marcas
                //Guarda la prueba en savedStateHandle con clave prueba
                //Guarda los datos del usuario en savedStateHandle con clave datosUsuario y navega a PantallaMarcas
                onItemClick = { prueba, datosUsuario ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("prueba", prueba)
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "datosUsuario",
                        datosUsuario
                    )
                    navController.navigate(PantallaMarcas)
                },
                navigateToBack = { navController.popBackStack() }
            )
        }

        composable<PantallaMarcas> {
            val datosUsuario = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<DatosUsuario>("datosUsuario")

            val prueba = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Prueba>("prueba")

            MostrarDatos(
                datosUsuario = datosUsuario,
                prueba = prueba,
                navigateToBack = { navController.popBackStack() }
            )
        }

        composable<PantallaContrasena> {
            PantallaContrasena { navController.popBackStack() }
        }
    }
}