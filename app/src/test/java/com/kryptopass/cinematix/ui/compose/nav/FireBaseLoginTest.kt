package com.kryptopass.cinematix.ui.compose.nav

import android.content.Context
import androidx.navigation.NavHostController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.kryptopass.common.nav.NavRoutes
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.reset
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class FireBaseLoginTest {
    @Mock
    private lateinit var auth: FirebaseAuth

    @Mock
    private lateinit var context: Context


    @Mock
    private lateinit var navController: NavHostController

    @Mock
    private lateinit var task: Task<AuthResult>

    @Captor
    private lateinit var onCompleteCaptor: ArgumentCaptor<OnCompleteListener<AuthResult>>

    @BeforeEach
    fun setup() {
        reset(auth, context, navController, task)

    }

    @Test
    fun shouldReturnTrueWhenUserIsAuthenticated() {

        val email = "testEmail@gmail.com"
        val password = "test123"

        `when`(auth.signInWithEmailAndPassword(email, password)).thenReturn(task)
        `when`(task.isSuccessful).thenReturn(true)

        loginWithEmailAndPassword(email, password, auth, context, navController)
        verify(task).addOnCompleteListener(onCompleteCaptor.capture())
        onCompleteCaptor.value.onComplete(task)

        verify(navController).navigate(NavRoutes.Movies.route)

    }

//    @Test
//    fun shouldReturnFalseWhenUserIsNotFound() {
//
//        val email = "testEmail@gmail.com"
//        val password = "test123"
//        val exceptionMessage = "Login failed"
//
//        `when`(auth.signInWithEmailAndPassword(email, password)).thenReturn(task)
//        `when`(task.isSuccessful).thenReturn(false)
//        `when`(task.exception).thenReturn(Exception(exceptionMessage))
//
//
//        loginWithEmailAndPassword(email, password, auth, context, navController)
//        verify(task).addOnCompleteListener(onCompleteCaptor.capture())
//        onCompleteCaptor.value.onComplete(task)
//
//        verify(navController, never()).navigate(NavRoutes.Movies.route)
//
//
//    }

}