package com.example.gpslocator

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController


@SuppressLint("QueryPermissionsNeeded")
@Composable
fun Foto(navController: NavController) {
    val context = LocalContext.current
    val locations = listOf(
        Location(R.drawable.rectangle_2, "Озеро"),
        Location(R.drawable.rectangle_3, "Горы"),
        Location(R.drawable.rectangle_4, "Отдых"),
        Location(R.drawable.rectangle_5, "Рыбалка"),
        Location(R.drawable.rectangle_6, "Водопад"),
        Location(R.drawable.rectangle_7, "Город")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Места",
            color = Color(70, 157, 62, 255),
            fontSize = 30.sp
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(top = 20.dp)
        ) {
            items(locations) { location ->
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(location.image),
                        contentDescription = location.title,
                        modifier = Modifier
                            .size(190.dp)
                    )
                    Text(
                        text = location.title,
                        color = Color(70, 157, 62, 255),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(top = 4.dp) // Уменьшено расстояние до текста
                    )
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        BottomAppBar(
            containerColor = Color(70, 157, 62, 255)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=55.7558,37.6176")) // Координаты (Москва)
                        intent.setPackage("com.google.android.apps.maps") // Указание на приложение Google Maps
                        context.startActivity(intent)
                    },
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.vector),
                        contentDescription = "foto_add",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

data class Location(val image: Int, val title: String)
