package com.github.laricchia.androidcomposablesamples.samplenavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.github.laricchia.androidcomposablesamples.Greeting
import com.github.laricchia.androidcomposablesamples.SampleColorChanger
import com.github.laricchia.androidcomposablesamples.SampleImageWithText

@Composable
fun FirstPage(elements: List<SampleElement>, navController: NavController) {
    Surface(
        Modifier
            .padding(16.dp)
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.LightGray)) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            elements.map { SampleRow(element = it) }
            Button(onClick = { navController.navigate("secondPage") }) {
                Text(text = "Next Page!")
            }
        }
    }
}

@Composable
fun SecondPage(navController: NavController) {
    // A surface container using the 'background' color from the theme
    Surface(
        color = MaterialTheme.colors.background,
        elevation = 4.dp,
        modifier = Modifier.padding(16.dp)
        ) {
        Column(
            Modifier
                // Set column height as 70% of the parent
                .fillMaxHeight(0.7f)
                // Enables the scrolling in the column
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
        ) {
            Text(text = "Just a second page :)")
            for (i in 1..11)
                Greeting("Android $i")
            SampleImageWithText("Sample text with image")
            SampleColorChanger(color = mutableStateOf(Color.Blue))
        }
    }
}

@Composable
fun SampleRow(element: SampleElement) = Row(Modifier.padding(16.dp)) {
    Column {
        Text(text = element.h1, fontSize = 20.sp)
        Text(text = element.h2)
    }
}

data class SampleElement(val h1: String, val h2: String)