package com.example.proyecto_gimnasia_lucas.database

import android.content.ContentValues
import android.content.Context

class DatosDBHelper(context: Context) : BDDGimnasia(context) {

    // Insertar o actualizar los datos del usuario
    fun insertarOActualizarDatos(usuarioId: Int, edad: Int, peso: Int, altura: Int, genero: Boolean): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("usuario_id", usuarioId)
            put("edad", edad)
            put("peso", peso)
            put("altura", altura)
            put("genero", if (genero) 1 else 0) // 1 = Hombre, 0 = Mujer
        }

        val cursor = db.rawQuery("SELECT id FROM datos WHERE usuario_id = ?", arrayOf(usuarioId.toString()))
        val exists = cursor.moveToFirst()
        cursor.close()

        return if (exists) {
            db.update("datos", values, "usuario_id = ?", arrayOf(usuarioId.toString())) > 0
        } else {
            db.insert("datos", null, values) > 0
        }
    }

    // Obtener los datos de un usuario
    fun getDatosUsuario(usuarioId: Int): EntDatos? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT edad, peso, altura, genero FROM datos WHERE usuario_id = ?", arrayOf(usuarioId.toString()))

        return if (cursor.moveToFirst()) {
            val edad = cursor.getInt(0)
            val peso = cursor.getInt(1)
            val altura = cursor.getInt(2)
            val genero = cursor.getInt(3) == 1
            cursor.close()
            EntDatos(usuarioId, edad, peso, altura, genero)
        } else {
            cursor.close()
            null
        }
    }

    // Borrar los datos de un usuario
    fun borrarDatosUsuario(usuarioId: Int): Int {
        val db = writableDatabase
        return db.delete("datos", "usuario_id = ?", arrayOf(usuarioId.toString()))
    }
}
