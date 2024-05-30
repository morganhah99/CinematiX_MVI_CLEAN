package com.kryptopass.cinematix.ui.compose.nav

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.kryptopass.common.nav.NavRoutes

fun loginWithEmailAndPassword(
    email: String,
    password: String,
    auth: FirebaseAuth,
    context: Context,
    navController: NavHostController
) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                navController.navigate(NavRoutes.Movies.route)
            }
            else {
                Toast.makeText(
                    context,
                    task.exception?.message ?: "Login failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
}