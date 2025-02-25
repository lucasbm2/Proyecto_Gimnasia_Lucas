package com.example.proyecto_gimnasia_lucas.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LoginDBHelper(context: Context): BDDGimnasia(context) {

    // Función para insertar un nuevo usuario
    fun insertarUsuario(usuario: EntLogin): Long {
        val db: SQLiteDatabase = this.writableDatabase
        var usuarioID = -1

        db.beginTransaction()

        try {
            val values = ContentValues()

            // Si el id es mayor que 0, se inserta el id en la base de datos
            if (usuario.id > 0) {
                values.put("id", usuario.id)
            }

            values.put("usuario", usuario.usuario)
            values.put("password", usuario.password)

            // Inserta el nuevo usuario en la tabla 'login'
            usuarioID = db.insertOrThrow("login", null, values).toInt()

            db.setTransactionSuccessful()

        } catch (e: Exception) {
            println(e.message)
        } finally {
            db.endTransaction()
        }
        return usuarioID.toLong()
    }

    // Función para actualizar los datos de un usuario
    fun actualizarUsuario(usuario: EntLogin): Long {
        val db: SQLiteDatabase = this.writableDatabase
        var usuarioID = -1

        if (usuario.id > 0) {
            db.beginTransaction()
            try {
                val values = ContentValues()

                values.put("usuario", usuario.usuario)
                values.put("password", usuario.password)

                // Actualiza la información del usuario en la tabla 'login'
                val rows = db.update("login", values, "id = ?", arrayOf(usuario.id.toString()))
                if (rows > 0) {
                    db.setTransactionSuccessful()
                    usuarioID = usuario.id
                }
            } catch (e: Exception) {
                println(e.message)
            } finally {
                db.endTransaction()
            }
        }
        return usuarioID.toLong()
    }

    // Función para obtener todos los usuarios
    fun getUsuarios(): List<EntLogin> {
        val db: SQLiteDatabase = this.readableDatabase
        val usuarios = mutableListOf<EntLogin>()
        val cursor: Cursor = db.rawQuery("SELECT * FROM login", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val usuario = cursor.getString(1)
                val password = cursor.getString(2)

                val usuarioObj = EntLogin( id, usuario, password)
                usuarios.add(usuarioObj)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return usuarios
    }

    // Función para borrar un usuario por su ID
    fun borrarUsuario(id: Int): Int {
        val db: SQLiteDatabase = this.writableDatabase
        var borrados = 0
        db.beginTransaction()

        try {
            borrados = db.delete("login", "id = ?", arrayOf(id.toString()))

            db.setTransactionSuccessful()
        } catch (e: Exception) {
            println(e.message)
        } finally {
            db.endTransaction()
        }
        return borrados
    }

    // Función para obtener un usuario por su ID
    fun getUsuario(id: Int): EntLogin? {
        val db: SQLiteDatabase = this.readableDatabase
        var usuario: EntLogin? = null
        val cursor: Cursor = db.rawQuery("SELECT * FROM login WHERE id = ?", arrayOf(id.toString()))

        if (cursor.moveToFirst()) {
            do {
                val usuarioId = cursor.getInt(0)
                val nombreUsuario = cursor.getString(1)
                val password = cursor.getString(2)

                usuario = EntLogin( id, nombreUsuario, password)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return usuario
    }

    // Función para verificar si un usuario existe con el nombre y contraseña dados
    fun verificarUsuario(usuario: String, password: String): Boolean {
        val db: SQLiteDatabase = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM login WHERE usuario = ? AND password = ?", arrayOf(usuario, password))

        val exists = cursor.moveToFirst() // Si encuentra algún registro, el usuario y contraseña son válidos
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

