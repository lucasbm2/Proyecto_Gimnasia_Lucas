package com.example.proyecto_gimnasia_lucas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.proyecto_gimnasia_lucas.ui.theme.Proyecto_Gimnasia_LucasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var isDarkTheme by remember { mutableStateOf(false) } // Variable para el tema oscuro

            Proyecto_Gimnasia_LucasTheme(darkTheme = isDarkTheme) {
                NavigationWrapper { isDarkTheme = !isDarkTheme }
            }
        }
    }
}
