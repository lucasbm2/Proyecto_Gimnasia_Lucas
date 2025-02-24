package com.example.proyecto_gimnasia_lucas.model

import androidx.annotation.DrawableRes
data class Prueba(
    val nombre: String,
    val descripcion: String,
    @DrawableRes val imagenResId: Int,
    val enlace: String
)
