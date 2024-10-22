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
fun RegistrationScreen(onNavigateToLogin: () -> Unit) { // Agregamos el parámetro para navegar al login
    val context = LocalContext.current

    var userEmail by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(false) }

    var userPassword by remember { mutableStateOf("") }
    var isPasswordValid by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }

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
                    DisplayImage()
                    EmailInput(
                        email = userEmail,
                        emailChange = {
                            userEmail = it
                            isEmailValid = Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()
                        },
                        isEmailValid
                    )
                    PasswordInput(
                        password = userPassword,
                        passwordChange = {
                            userPassword = it
                            isPasswordValid = userPassword.length >= 6
                        },
                        isPasswordVisible = isPasswordVisible,
                        togglePasswordVisibility = { isPasswordVisible = !isPasswordVisible },
                        isPasswordValid = isPasswordValid
                    )
                    RegisterButton(
                        context = context,
                        isEmailValid = isEmailValid,
                        isPasswordValid = isPasswordValid
                    )

                    // Botón para navegar de regreso a la pantalla de login
                    NavigateToLoginButton(onNavigateToLogin)
                }
            }
        }
    }
}

// Botón para navegar a la pantalla de login
@Composable
fun NavigateToLoginButton(onNavigateToLogin: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onNavigateToLogin() } // Llama a la función para navegar
        ) {
            Text(text = "Ya tengo cuenta, Iniciar sesión")
        }
    }
}

// Código para el campo del correo
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInput(
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

// Código para la contraseña
@Composable
fun PasswordInput(
    password: String,
    passwordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    togglePasswordVisibility: () -> Unit,
    isPasswordValid: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = passwordChange,
            maxLines = 1,
            singleLine = true,
            label = { Text(text = "Contraseña") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val icon = if (isPasswordVisible) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                }
                IconButton(onClick = togglePasswordVisibility) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Ver contraseña"
                    )
                }
            },
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            colors = if (isPasswordValid) {
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

// Código para el botón de registrar
@Composable
fun RegisterButton(
    context: Context,
    isEmailValid: Boolean,
    isPasswordValid: Boolean
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { performRegistration(context) },
            enabled = isEmailValid && isPasswordValid
        ) {
            Text(text = "Registrar")
        }
    }
}

// Fake alerta de registro
fun performRegistration(context: Context) {
    Toast.makeText(context, "FAKE REGISTER :)", Toast.LENGTH_LONG).show()
}

@Composable
fun DisplayImage() {
    Row(
        Modifier.fillMaxWidth().padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.width(100.dp),
            painter = painterResource(id = R.drawable.registrado),
            contentDescription = "Imagen registro"
        )
    }
}
