package com.bookdel.app.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bookdel.app.Data.Book

@Composable
fun MainScreen(innerPadding: PaddingValues) {
    BookLayout(innerPadding)
}

@Composable
fun BookLayout(padding: PaddingValues) {
    val items = fetchBooks()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 200.dp),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.padding(padding)
    ) {
        items(items.size) { index ->
            OutlinedCard (
                border = BorderStroke(1.dp, color = Color.Black),
                modifier = Modifier
                    .size(height = 240.dp, width = 80.dp)
                    .padding(5.dp)
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        imageVector = items[index].image,
                        contentDescription = "Book $index",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.weight(0.8f)
                    )
                    HorizontalDivider()
                    Text("${items[index].name}", Modifier.padding(3.dp), fontSize = 15.sp, textAlign = TextAlign.Center)
                    Text("₹${items[index].cost}",  fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 4.dp))
                }
            }
        }
    }
}

fun fetchBooks(): List<Book> {
    val list = mutableListOf<Book>()
    for(i in 1..100) {
        list.add(
            Book(
                name = "Book $i",
                image = Icons.Outlined.Book,
                cost = i*100
            )
        )
    }

    return list.toList()
}