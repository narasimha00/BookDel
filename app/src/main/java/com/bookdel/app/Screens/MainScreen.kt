package com.bookdel.app.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.bookdel.app.R
import androidx.compose.ui.res.painterResource

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
        items(items.size, key = {items[it].id}) { index ->
            OutlinedCard (
                border = BorderStroke(1.dp, color = if(isSystemInDarkTheme()) Color.White else Color.Black),
                modifier = Modifier
                    .size(height = 240.dp, width = 80.dp)
                    .padding(5.dp)
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        painter = painterResource(id = items[index].imageRes),
                        contentDescription = "Book ${items[index].name}",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(175.dp)
                            .padding(top = 10.dp)
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
    var bookList = listOf<Book>(
        Book(
            name = "Atomic Habits",
            imageRes = R.drawable.atomichabits,
            cost = 500
        ),
        Book(
            name = "The Alchemist",
            imageRes = R.drawable.thealchemist,
            cost = 300
        ),
        Book(
            name = "Rich Dad Poor Dad",
            imageRes = R.drawable.richdadpoordad,
            cost = 400
        ),
        Book(
            name = "The Psychology of Money",
            imageRes = R.drawable.thepsychologyofmoney,
            cost = 450
        ),
        Book(
            name = "Deep Work",
            imageRes = R.drawable.deepwork,
            cost = 600
        ),
        Book(
            name = "Verity",
            imageRes = R.drawable.verity,
            cost = 500
        ),Book(
            name = "All Fours",
            imageRes = R.drawable.allfours,
            cost = 649
        ),
        Book(
            name = "Headshot",
            imageRes = R.drawable.headshot,
            cost = 740
        ),
        Book(
            name = "Intermezzo",
            imageRes = R.drawable.intermezzo,
            cost = 638
        ),
        Book(
            name = "James",
            imageRes = R.drawable.james,
            cost = 999
        ),
        Book(
            name = "Onyx Strom",
            imageRes = R.drawable.onyxstorm,
            cost = 686
        ),
        Book(
            name = "Our Evenings",
            imageRes = R.drawable.ourevenings,
            cost = 700
        ),
       Book(
           name = "The God Of The Woods",
           imageRes = R.drawable.thegodofthewoods,
           cost = 459
       ),
        Book(
            name = "The Nightingale",
            imageRes = R.drawable.thenightingale,
            cost = 899
        ),
        Book(
            name = "The Physchology Of Money",
            imageRes = R.drawable.thepsychologyofmoney,
            cost = 750
        ),
        Book(
            name = "The Wedding People",
            imageRes = R.drawable.theweddingpeople,
            cost = 689
        ),
        Book(
            name = "Wandering Star",
            imageRes = R.drawable.wanderingstars,
            cost = 599
        )
    )

    return bookList
    }


