package com.example.marketplace

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketScreen(
    onAgregarAlCarrito: (Producto) -> Unit,
    onVerCarrito: () -> Unit,
    onIrCuenta: () -> Unit
) {
    // Lista de productos
    val productos = listOf(
        Producto(nombre = "Telefono", precio = 500.0, descripcion = "movil de ultima generacion"),
        Producto(nombre = "Portatil", precio = 1200.0, descripcion = "portatil de ultima generacion"),
        Producto(nombre = "Auriculares", precio = 150.0, descripcion = "Cancelacion de ruido"),
        Producto(nombre = "Tablet", precio = 400.0, descripcion = "Ideal para estudiar")
    )

    var textoBusqueda by remember { mutableStateOf("") }

    val productosFiltrados = productos.filter {
        it.nombre.contains(textoBusqueda, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Avito") },
                actions = {

                    // carrito
                    IconButton(onClick = onVerCarrito) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Carrito"
                        )
                    }

                    // cuenta
                    IconButton(onClick = onIrCuenta) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Cuenta"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            // buscador
            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = { textoBusqueda = it },
                label = { Text("Buscar producto") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // productos
            LazyColumn {
                items(productosFiltrados) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(producto.nombre, fontWeight = FontWeight.Bold)
                            Text(producto.descripcion)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("${producto.precio} €", fontWeight = FontWeight.Bold)

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = { onAgregarAlCarrito(producto) },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Añadir al carrito")
                            }
                        }
                    }
                }
            }
        }
    }
}
