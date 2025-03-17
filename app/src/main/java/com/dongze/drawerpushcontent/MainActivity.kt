package com.dongze.drawerpushcontent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dongze.drawerpushcontent.ui.theme.DrawerPushContentTheme

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

    Box(modifier = Modifier.fillMaxSize()) {
        DrawerContainer(
            isDrawerOpened = isDrawerOpened,
            onSwipe = { toOpen -> isDrawerOpened = toOpen }
        ) {
            Drawer()
            Body()
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
fun Drawer() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text("Drawer Content", color = Color.White)
    }
}

@Composable
fun Body() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Main Content", color = Color.Black)
            /*Button(onClick = onMenuClick) {
                Text("Open Drawer")
            }*/
        }
    }
}
