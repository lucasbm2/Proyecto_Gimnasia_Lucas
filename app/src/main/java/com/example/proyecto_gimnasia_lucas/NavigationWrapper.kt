package com.example.proyecto_gimnasia_lucas


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composecatalog.navigation.PantallaCalculoIMC
import com.example.composecatalog.navigation.PantallaInicial
import com.example.composecatalog.navigation.PantallaPrincipal
import com.example.composecatalog.navigation.PantallaLogin
import com.example.composecatalog.navigation.PantallaNotas
import com.example.composecatalog.navigation.PruebaRV
import com.example.proyecto_gimnasia_lucas.model.ItemPrueba
import com.example.proyecto_gimnasia_lucas.model.PruebasList


@Composable

fun NavigationWrapper() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PantallaInicial) {
        composable<PantallaInicial> {
            PantallaInicial({ navController.navigate(PantallaLogin) }
            )
        }
        composable<PantallaLogin> {
            PantallaLogin(
                navigateToPantallaPrincipal = { navController.navigate(PantallaPrincipal) },
                navigateToBack = { navController.popBackStack() }
            )
        }

        composable<PantallaPrincipal> {
            PantallaPrincipal(
                navigateToNotas = { navController.navigate(PantallaNotas) },
                navigateToPruebas = { navController.navigate(PruebaRV) },
                navigateToCalculoIMC = { navController.navigate(PantallaCalculoIMC) }
            )
        }
        composable<PantallaNotas> {
            PantallaNotas { navController.navigate(PantallaPrincipal) }
        }
        composable<PantallaCalculoIMC> {
            PantallaCalculoIMC { navController.navigate(PantallaPrincipal) }
        }

        composable<PruebaRV> {
            PruebasList(
                onItemClick = { prueba -> navController.navigate( PruebaRV) },
                navigateToBack = { navController.navigate(PantallaPrincipal) }

            )
        }

    }
}
