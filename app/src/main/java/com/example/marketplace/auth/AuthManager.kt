package com.example.marketplace

import com.google.firebase.auth.FirebaseAuth

class AuthManager {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun loginUsuario(email: String, pass: String, onResult: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                onResult(task.isSuccessful)
            }
    }

    fun cerrarSesion() {
        auth.signOut()
    }

    fun obtenerEmail(): String? {
        return auth.currentUser?.email
    }

}
