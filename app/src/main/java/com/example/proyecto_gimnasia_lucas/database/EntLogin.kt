package com.example.proyecto_gimnasia_lucas.database

//Clase de datos para guardar los datos del login del usuario
data class EntLogin(
    var id: Int = 0,
    var usuario: String,
    var password: String
) {
    //ID a 0 porque es autoincremental
    constructor(usuario: String, password: String) : this(0, usuario, password)

    override fun toString(): String {
        return "EntLogin(id=$id, usuario='$usuario', password='$password')"
    }
}
