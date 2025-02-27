package com.example.proyecto_gimnasia_lucas.database

import android.content.ContentValues
import android.content.Context

class DatosDBHelper(context: Context) : BDDGimnasia(context) {

    //  Funcion para insertar nuevos datos de usuario o actualizar los existentes
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

        //En la tabla datos, mete los valores recogidos buscando la ID del usuario y añade los valores a actualizar
        return if (exists) {
            db.update("datos", values, "usuario_id = ?", arrayOf(usuarioId.toString())) > 0
        } else {
            db.insert("datos", null, values) > 0
        }
    }

}
