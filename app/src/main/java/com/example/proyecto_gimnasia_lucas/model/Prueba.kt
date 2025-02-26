package com.example.proyecto_gimnasia_lucas.model

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Prueba(
    val nombre: String,
    val descripcion: String,
    @DrawableRes val imagenResId: Int,
    val enlace: String
) : Serializable
