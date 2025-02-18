package com.bookdel.app.Screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bookdel.app.Authentication.AuthState
import com.bookdel.app.Authentication.AuthViewModel
import com.bookdel.app.Navigation.RootNavigation

@Composable
fun LoginScreen(authViewModel: AuthViewModel, navController: NavHostController) {
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(authState.value) {
        when(authState.value) {
            is AuthState.Authenticated -> {
                navController.popBackStack()
                navController.navigate(RootNavigation.Home)
            }
            is AuthState.Error -> {
                Toast.makeText(context, (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.BottomCenter
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(0.96f)
                .height(IntrinsicSize.Min)
                .shadow(4.dp, shape = RoundedCornerShape(24.dp)), // Apply elevation properly
            shape = RoundedCornerShape(24.dp), // Rounded edges
            border = BorderStroke(2.dp, Color.Gray),
        ) {
            val rainbowColors: List<Color> = listOf(
                Color(0xFF9400D3), // Violet
                Color(0xFF4B0082), // Indigo
                Color(0xFF0000FF), // Blue
                Color(0xFFFF7F00), // Orange
                Color(0xFFFF0000)  // Red
            )
            val brush = remember {
                Brush.linearGradient(
                    colors = rainbowColors
                )
            }
            var email by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(60.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    "Hello there, ",
                    fontSize = 36.sp,
                )
                Text(
                    "Welcome to Book Del!\n",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        brush = brush
                    )
                )
                // username
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = {
                        Text("Enter username", fontWeight = FontWeight.Medium, color = Color.Gray)
                    },
                    leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Person",
                            )
                        }
                    },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.SansSerif,
                        textMotion = TextMotion.Animated
                    )
                )


                // password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    visualTransformation = PasswordVisualTransformation('*'),
                    label = {
                        Text("Enter password", fontWeight = FontWeight.Medium, color = Color.Gray)
                    },
                    leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Lock",
                            )
                        }
                    },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.SansSerif,
                        textMotion = TextMotion.Animated
                    )
                )


                // login and signup buttons row
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 40.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    OutlinedButton(
                        onClick = {
                            println("Log in button clicked!")
                            if(email == "" || password == "") {
                                Toast.makeText(context, "Kindly fill all the fields!", Toast.LENGTH_SHORT).show()
                            } else {
                                authViewModel.login(email, password)
                            }
                        },
                        modifier = Modifier
                            .weight(0.6f),
                        enabled = authState.value != AuthState.Loading

                    ) {
                        Text("Login")
                    }

                    Spacer(
                        modifier = Modifier
                            .width(25.dp)
                    )
                    OutlinedButton(
                        onClick = {
                            println("Sign up button clicked!")
                            if(email == "" || password == "") {
                                Toast.makeText(context, "Kindly fill all the fields!", Toast.LENGTH_SHORT).show()
                            } else {
                                authViewModel.signup(email, password)
                            }
                        },
                        modifier = Modifier
                            .weight(0.7f),
                        colors = ButtonColors(
                            containerColor = Color(0xFFf19938),
                            contentColor = Color.White,
                            disabledContainerColor = Color.Gray,
                            disabledContentColor = Color.Black
                        ),
                        enabled = authState.value != AuthState.Loading
                    ) {
                        Text("Sign up")
                    }
                }
            }
        }
    }
}