package com.example.marketplace

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carrito_table")
data class Producto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val precio: Double,
    val descripcion: String
)
