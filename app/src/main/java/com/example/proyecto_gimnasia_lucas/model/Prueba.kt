package com.example.proyecto_gimnasia_lucas.model

import androidx.annotation.DrawableRes
import java.io.Serializable

// Clase de datos para guardar datos de una prueba deportiva
data class Prueba(
    val nombre: String,
    val descripcion: String,
    @DrawableRes val imagenResId: Int,
    val enlace: String
) : Serializable
