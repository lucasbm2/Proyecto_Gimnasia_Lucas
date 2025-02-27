package com.example.proyecto_gimnasia_lucas.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class LoginDBHelper(context: Context): BDDGimnasia(context) {

    // Función para insertar un nuevo usuario
    fun insertarUsuario(usuario: EntLogin): Long {
        val db: SQLiteDatabase = this.writableDatabase
        var usuarioID = -1

        db.beginTransaction()

        try {
            val values = ContentValues()


            if (usuario.id > 0) {
                values.put("id", usuario.id)
            }

            values.put("usuario", usuario.usuario)
            values.put("password", usuario.password)


            usuarioID = db.insertOrThrow("login", null, values).toInt()

            db.setTransactionSuccessful()

        } catch (e: Exception) {
            println(e.message)
        } finally {
            db.endTransaction()
        }
        return usuarioID.toLong()
    }


    fun actualizarUsuario(usuario: String, nuevaContrasena: String): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put("password", nuevaContrasena)

        val rowsUpdated = db.update("login", values, "usuario = ?", arrayOf(usuario))

        return rowsUpdated > 0
    }



    // Función para verificar si un usuario existe con el nombre y contraseña dados
    fun verificarUsuario(usuario: String, password: String): Boolean {
        val db: SQLiteDatabase = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM login WHERE usuario = ? AND password = ?", arrayOf(usuario, password))

        val exists = cursor.moveToFirst()
        cursor.close()
        return exists
    }

    fun getNombreUsuarioPorNombre(usuario: String): String? {
        val db: SQLiteDatabase = readableDatabase
        var nombreUsuarioEncontrado: String? = null
        val cursor: Cursor = db.rawQuery("SELECT usuario FROM login WHERE usuario = ?", arrayOf(usuario))

        if (cursor.moveToFirst()) {
            nombreUsuarioEncontrado = cursor.getString(0)
        }
        cursor.close()
        return nombreUsuarioEncontrado
    }

}

