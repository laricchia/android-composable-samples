 package com.github.laricchia.androidcomposablesamples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.laricchia.androidcomposablesamples.samplenavigation.FirstPage
import com.github.laricchia.androidcomposablesamples.samplenavigation.SampleElement
import com.github.laricchia.androidcomposablesamples.samplenavigation.SecondPage
import com.github.laricchia.androidcomposablesamples.ui.theme.AndroidComposableSamplesTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidComposableSamplesTheme {

                val drawableState = rememberScaffoldState()

                Scaffold(
                    scaffoldState = drawableState,
                    topBar = {
                        TopAppBar {
                            Text(text = "Hello Github!", Modifier.padding(16.dp))
                        }
                    },
                    drawerContent = {
                        Button(
                            onClick = { drawableState.drawerState.close() },
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = "Close me PLZ")
                        }
                    },
                    bodyContent = {
                        val controller = rememberNavController()
                        NavHost(
                            navController = controller,
                            startDestination = "firstPage"
                        ) {
                            composable("firstPage") {
                                FirstPage(listOf(SampleElement("Title", "Content")), controller)
                            }
                            composable("secondPage") { SecondPage(controller) }
                        }
                    }
                )

            }
        }


        /*
        val colorState = mutableStateOf(Color.DarkGray)
        val colors = listOf(Color.Blue, Color.Green, Color.Red, Color.DarkGray)

        CoroutineScope(Dispatchers.Default).launch {
            var count = 0
            while (true) {
                delay(500)
                colorState.value = colors[count]
                count ++
                count %= colors.size
            }
        }*/
    }
}

@Composable
fun Greeting(name: String) {
    Row( Modifier.padding(8.dp) ) {
        Text(
            text = "Hello $name!",
            // Set text size to 20 sp
            fontSize = TextUnit(20.sp.packedValue),
            // Set text alignment to center
            textAlign = TextAlign.Center,
            modifier = Modifier
                // xml's match_parent
                .fillMaxWidth()
        )
    }
}

 @Composable
 fun SampleImageWithText(text : String = "default...lorem ipsum?") {

     val remHeight = 12.dp
     
     Row(
         Modifier
             .padding(4.dp)
             .wrapContentHeight() ) {
         Image(
             painter = painterResource(id = R.drawable.ic_launcher_foreground),
             contentDescription = "Just an image",
             contentScale = ContentScale.Crop,
             modifier = Modifier.height(remHeight)
         )

         Text(text = text,
             modifier = Modifier.onGloballyPositioned {
                 //remHeight = Dp(it.size.height.toFloat())
             },
             color = Color.DarkGray)
     }
 }

 @Composable
 fun SampleColorChanger(color : MutableState<Color>) {
     // CIT Southpark
     val membeeer by remember(calculation = { color })
     Row(Modifier.padding(16.dp)) {
        Text(text = "Test changing color", color = membeeer)
     }
 }

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidComposableSamplesTheme {
        Column {
            Greeting("Android")
            SampleImageWithText("Test!")
            SampleColorChanger(color = mutableStateOf(Color.DarkGray))
        }
    }
}