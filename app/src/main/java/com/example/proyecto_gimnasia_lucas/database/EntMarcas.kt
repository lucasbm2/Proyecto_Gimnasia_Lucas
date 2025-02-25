package com.example.proyecto_gimnasia_lucas.database

data class EntMarcas(
    var id: Int = 0,
    var abdominales: Int,
    var flexibilidad: Int,
    var testcooper: Int,
    var velocidad: Int,
    var lanzamientobalon: Int
) {

    constructor(abdominales: Int, flexibilidad: Int, testcooper: Int, velocidad: Int, lanzamientobalon: Int) : this(0, abdominales, flexibilidad, testcooper, velocidad, lanzamientobalon)

    override fun toString(): String {
        return "EntMarcas(id=$id, abdominales=$abdominales, flexibilidad=$flexibilidad, testcooper=$testcooper, velocidad=$velocidad, lanzamientobalon=$lanzamientobalon)"
    }
}
