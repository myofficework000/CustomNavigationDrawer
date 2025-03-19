package com.code4galaxy.drawerpushcontent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.code4galaxy.drawerpushcontent.ui.theme.DrawerPushContentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrawerPushContentTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    var isDrawerOpened by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Main Content") }
    var selectedItem by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        DrawerContainer(
            isDrawerOpened = isDrawerOpened,
            onSwipe = { toOpen -> isDrawerOpened = toOpen }
        ) {
            Drawer(selectedItem) { text ->
                selectedText = text
                selectedItem = text
                isDrawerOpened = false
            }
            Body(selectedText)
        }

        IconButton(
            onClick = { isDrawerOpened = !isDrawerOpened },
            modifier = Modifier.align(Alignment.TopStart).padding(top = 30.dp)
        ) {
            Icon(
                if (isDrawerOpened) Icons.Default.Close else Icons.Default.Menu,
                contentDescription = "Open Drawer"
            )
        }
    }
}

@Composable
fun Drawer(selectedItem: String, onItemClick: (String) -> Unit) {
    Column(modifier = Modifier.background(Color.White)) {
        // Header Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.AccountCircle, contentDescription = "Profile", tint = Color.DarkGray)
        }

        // Drawer Items
        val items = listOf("Home", "Profile", "Settings", "Notifications", "Help")
        items.forEach { item ->
            val backgroundColor = if (item == selectedItem) Color.Gray else Color.White
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .padding(16.dp)
                    .clickable { onItemClick(item) }
            ) {
                Text(text = item, color = Color.Black)
            }
        }

        // Shortcuts Section
        Text(
            text = "Shortcuts",
            modifier = Modifier.padding(16.dp),
            color = Color.DarkGray
        )
        val shortcuts = listOf("Favorites", "Recent")
        shortcuts.forEach { shortcut ->
            val backgroundColor = if (shortcut == selectedItem) Color.Gray else Color.White
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .padding(16.dp)
                    .clickable { onItemClick(shortcut) }
            ) {
                Text(text = shortcut, color = Color.Black)
            }
        }
    }
}

@Composable
fun Body(selectedText: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(selectedText, color = Color.White, fontSize = 30.sp)
        }
    }
}

@Composable
fun DrawerItem(icon: ImageVector, text: String, onClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick(text) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = text, tint = Color.White)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, color = Color.White)
    }
}
