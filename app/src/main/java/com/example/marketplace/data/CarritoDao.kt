package com.example.marketplace

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CarritoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun agregarAlCarrito(producto: Producto)

    @Query("SELECT * FROM carrito_table")
    fun obtenerTodos(): Flow<List<Producto>>

    @Delete
    suspend fun eliminar(producto: Producto)
}
