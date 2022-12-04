package com.radenyaqien.composecampdicoding.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutScreen(
    onBackClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 4.dp,
                title = {
                    Text("About Me")
                },
                backgroundColor = MaterialTheme.colors.primarySurface,
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                })
        }
    ) {
        AboutContent()
    }
}

@Composable
fun AboutContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = com.radenyaqien.composecampdicoding.R.drawable.ic_profile),
            contentDescription = "About Picture",
            modifier = Modifier.clip(RoundedCornerShape(16.dp)),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Muhammad Ainul Yaqin",
            fontFamily = FontFamily.Monospace,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "myaqien27@gmail.com")
    }
}

@Preview
@Composable
fun AboutPreview() {
    AboutScreen {

    }
}