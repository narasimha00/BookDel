package com.bookdel.app.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun loginScreen() {
    val rainbowColors: List<Color> = listOf(
        Color(0xFF9400D3), // Violet
        Color(0xFF4B0082), // Indigo
        Color(0xFF0000FF), // Blue
//        Color(0xFF00FF00), // Green
//        Color(0xFFFFFF00), // Yellow
        Color(0xFFFF7F00), // Orange
        Color(0xFFFF0000)  // Red
    )
    val brush = remember {
        Brush.linearGradient(
            colors = rainbowColors
        )
    }
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxHeight()
            .padding(60.dp),
        verticalArrangement = Arrangement.Center
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
            value = username,
            onValueChange = { username = it },
            label = {
                Text("Enter username", fontWeight = FontWeight.Medium, color = Color.Gray)
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
        ){
            OutlinedButton(
                onClick = {
                    println("Sign up button clicked!")
                },
                modifier = Modifier
                    .weight(0.5f),

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
                },
                modifier = Modifier
                    .weight(0.7f),
                colors = ButtonColors(
                    containerColor = Color(0xFFf19938),
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.Black
                )
            ) {
                Text("Sign up")
            }
        }
    }
}