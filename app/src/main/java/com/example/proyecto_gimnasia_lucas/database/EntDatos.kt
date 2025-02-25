package com.example.proyecto_gimnasia_lucas.database

data class EntDatos(
    var id: Int = 0,
    var edad: Int,
    var peso: Int,
    var altura: Int,
    var sexo: Boolean
) {

    constructor(edad: Int, peso: Int, altura: Int, sexo: Boolean) : this(0, edad, peso, altura, sexo)

    override fun toString(): String {
        return "EntDatos(id=$id, edad=$edad, peso=$peso, altura=$altura, sexo=$sexo)"
    }
}
