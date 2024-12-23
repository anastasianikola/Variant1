package com.example.gpslocator

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Start(navController: NavController, context: Context) {
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val database = remember { AppDatabase.create(context) }

    val userDao = database.userDao()

    val isLoading = remember { mutableStateOf(false) }
    val error = remember { mutableStateOf("") }

    // Функция для обновления или вставки пользователя в базу данных
    fun saveUserToDatabase(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = userDao.getUser(username, password)
            if (user == null) {
                userDao.insertUser(User(username = username, password = password))
            } else {
                userDao.updateUser(User(username = username, password = password))
            }
        }
    }

    // TopAppBar
    TopAppBar(
        title = { Text("GPS локатор") },
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Filled.Settings, contentDescription = "Настройки", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(70, 157, 62, 255),
            titleContentColor = Color.White
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.rectangle),
            contentDescription = "rectangle_map",
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
                .height(150.dp)
                .background(Color(70, 157, 62, 255).copy(alpha = 0.5f))
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Default.AccountBox,
                contentDescription = "Логин",
                tint = Color(70, 157, 62, 255),
                modifier = Modifier
                    .size(50.dp)
            )
            TextField(
                value = login.value,
                onValueChange = {
                    login.value = it
                    saveUserToDatabase(it, password.value) // Сохранение изменений в базу
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Default.Lock,
                contentDescription = "Пароль",
                tint = Color(70, 157, 62, 255),
                modifier = Modifier
                    .size(50.dp)
            )
            TextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                    saveUserToDatabase(login.value, it) // Сохранение изменений в базу
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
        }

        // Отображение ошибки
        if (error.value.isNotEmpty()) {
            Text(
                text = error.value,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                navController.navigate("foto")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
                .height(70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(70, 157, 62, 255),
                contentColor = Color.White
            )
        ) {
            Text(text = "Start", fontSize = 30.sp)
        }
    }
}
