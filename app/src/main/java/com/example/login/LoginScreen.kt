@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.login

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(onNavigateToRegister: () -> Unit) { // Añadimos un parámetro para la navegación
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var isValidEmail by remember { mutableStateOf(false) }

    var contrasena by remember { mutableStateOf("") }
    var isValidPassword by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff3662c9))
    ) {
        Column(
            Modifier
                .align(Alignment.Center)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Card(
                Modifier.padding(12.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(20.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    RowImage()
                    RowEmail(
                        email = email,
                        emailChange = {
                            email = it
                            isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        },
                        isValidEmail
                    )
                    RowPassword(
                        contrasena = contrasena,
                        passwordChange = {
                            contrasena = it
                            isValidPassword = contrasena.length >= 6
                        },
                        passwordVisible = passwordVisible,
                        passwordVisibleChange = { passwordVisible = !passwordVisible },
                        isValidPassword = isValidPassword
                    )
                    RowButtonLogin(
                        context = context,
                        isValidEmail = isValidEmail,
                        isValidPassword = isValidPassword
                    )

                    // Añadimos un nuevo botón para redirigir al registro
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(onClick = onNavigateToRegister) {
                            Text(text = "¿No tienes una cuenta? Regístrate aquí.")
                        }
                    }
                }
            }
        }
    }
}

// Código para el campo del correo
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowEmail(
    email: String,
    emailChange: (String) -> Unit,
    isValid: Boolean
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = emailChange,
            label = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            maxLines = 1,
            singleLine = true,
            colors = if (isValid) {
                TextFieldDefaults.outlinedTextFieldColors(
                    focusedLabelColor = Color.Green,
                    focusedBorderColor = Color.Green
                )
            } else {
                TextFieldDefaults.outlinedTextFieldColors(
                    focusedLabelColor = Color.Red,
                    focusedBorderColor = Color.Red
                )
            }
        )
    }
}

// Contraseña
@Composable
fun RowPassword(
    contrasena: String,
    passwordChange: (String) -> Unit,
    passwordVisible: Boolean,
    passwordVisibleChange: () -> Unit,
    isValidPassword: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = contrasena,
            onValueChange = passwordChange,
            maxLines = 1,
            singleLine = true,
            label = { Text(text = "Contraseña") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                }
                IconButton(onClick = passwordVisibleChange) {
                    Icon(
                        imageVector = image,
                        contentDescription = "Ver contraseña"
                    )
                }
            },
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            colors = if (isValidPassword) {
                TextFieldDefaults.outlinedTextFieldColors(
                    focusedLabelColor = Color.Green,
                    focusedBorderColor = Color.Green
                )
            } else {
                TextFieldDefaults.outlinedTextFieldColors(
                    focusedLabelColor = Color.Red,
                    focusedBorderColor = Color.Red
                )
            }
        )
    }
}

@Composable
fun RowButtonLogin(
    context: Context,
    isValidEmail: Boolean,
    isValidPassword: Boolean
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { login(context) },
            enabled = isValidEmail && isValidPassword
        ) {
            Text(text = "Iniciar Sesión")
        }
    }
}

fun login(context: Context) {
    Toast.makeText(context, "FAKE LOGIN :)", Toast.LENGTH_LONG).show()
}

@Composable
fun RowImage() {
    Row(
        Modifier.fillMaxWidth().padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.width(100.dp),
            painter = painterResource(id = R.drawable.bolsa_de_la_compra),
            contentDescription = "Imagen login"
        )
    }
}
