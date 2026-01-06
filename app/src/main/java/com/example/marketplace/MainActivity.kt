package com.example.marketplace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.marketplace.ui.screens.LoginScreen
import com.example.marketplace.ui.theme.MarketplaceTheme
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Room
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "marketplace-db"
        ).build()

        val dao = db.carritoDao()

        // Firebase Auth
        val authManager = AuthManager()

        setContent {
            MarketplaceTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {

                    // login
                    composable("login") {
                        LoginScreen(
                            onLoginSuccess = {
                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }

                    // productos
                    composable("home") {
                        MarketScreen(
                            onAgregarAlCarrito = { producto ->
                                lifecycleScope.launch {
                                    dao.agregarAlCarrito(producto)
                                }
                            },
                            onVerCarrito = {
                                navController.navigate("carrito")
                            },
                            onIrCuenta = {
                                navController.navigate("cuenta")
                            }
                        )
                    }

                    // carrito
                    composable("carrito") {
                        val listaProductos by dao.obtenerTodos()
                            .collectAsState(initial = emptyList())

                        CarritoScreen(
                            productosEnCarrito = listaProductos,
                            onEliminarProducto = { producto ->
                                lifecycleScope.launch {
                                    dao.eliminar(producto)
                                }
                            },
                            onVolverHome = {
                                navController.navigate("home") {
                                    popUpTo("home") { inclusive = true }
                                }
                            }
                        )
                    }

                    // cuenta
                    composable("cuenta") {
                        CuentaScreen(
                            emailUsuario = authManager.obtenerEmail(),
                            onCerrarSesion = {
                                authManager.cerrarSesion()
                                navController.navigate("login") {
                                    popUpTo(0)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
