package com.example.proyecto_gimnasia_lucas.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class BDDGimnasia(context: Context) : SQLiteOpenHelper(context, "bddGimnasia", null, 1) {

    private val crearTablaLogin = "CREATE TABLE login(id INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT, password TEXT)"
    private val borrarTablaLogin = "DROP TABLE IF EXISTS login"

    private val crearTablaDatos = "CREATE TABLE datos(id INTEGER PRIMARY KEY AUTOINCREMENT, edad INTEGER, peso INTEGER, altura INTEGER, sexo BOOLEAN)"
    private val borrarTablaDatos = "DROP TABLE IF EXISTS datos"

    private val crearTablaMarcas = "CREATE TABLE marcas(id INTEGER PRIMARY KEY AUTOINCREMENT, abdominales INTEGER, flexibilidad INTEGER, testcooper INTEGER, velocidad INTEGER, lanzamientobalon INTEGER)"
    private val borrarTablaMarcas = "DROP TABLE IF EXISTS marcas"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(crearTablaLogin)
        db?.execSQL(crearTablaDatos)
        db?.execSQL(crearTablaMarcas)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(borrarTablaLogin)
        db?.execSQL(borrarTablaDatos)
        db?.execSQL(borrarTablaMarcas)
        onCreate(db)
    }
}
