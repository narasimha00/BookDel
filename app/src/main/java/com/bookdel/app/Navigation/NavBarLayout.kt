package com.bookdel.app.Navigation

import androidx.collection.scatterSetOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bookdel.app.R

@Composable
fun NavBarHeader() {
    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.applogo), contentDescription = "user profile", modifier = Modifier.size(100.dp).padding(10.dp).clip(CircleShape), contentScale = ContentScale.Crop)
        Text(text = "Username", modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun NavBarBody(
    items: List<NavigationItem>,
    onClick: (NavigationItem) -> Unit
) {
    var selectedIndex by remember { mutableStateOf(0) }
    items.forEachIndexed {index, item ->
        NavigationDrawerItem(
            label = {
                Text(text = item.title)
            },
            selected = selectedIndex == index,
            onClick = {
                selectedIndex = index
                onClick(item)
            },
            icon = {
                Icon(imageVector = if(selectedIndex == index) item.selectedIcon else item.unselectedIcon, contentDescription = item.title + "icon")
            },
            modifier = Modifier
                .padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}