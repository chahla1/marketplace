package com.example.marketplace

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(
    productosEnCarrito: List<Producto>,
    onEliminarProducto: (Producto) -> Unit,
    onVolverHome: () -> Unit
) {
    val total = productosEnCarrito.sumOf { it.precio }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrito") },
                actions = {
                    IconButton(onClick = onVolverHome) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Volver a casa"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            if (productosEnCarrito.isEmpty()) {
                Text("El carrito está vacío")
            } else {

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(productosEnCarrito) { producto ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(producto.nombre, fontWeight = FontWeight.Bold)
                                    Text("${producto.precio} €")
                                }

                                IconButton(onClick = { onEliminarProducto(producto) }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Eliminar"
                                    )
                                }
                            }
                        }
                    }
                }

                Divider()

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Total: $total €",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {},//simulacion de compra
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Finalizar pedido")
                }
            }
        }
    }
}
