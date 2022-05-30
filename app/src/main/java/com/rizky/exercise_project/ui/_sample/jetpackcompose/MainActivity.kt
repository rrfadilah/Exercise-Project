package com.rizky.exercise_project.ui._sample.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rizky.exercise_project.ui._sample.jetpackcompose.ui.theme.ExerciseProjectTheme
import com.rizky.exercise_project.ui._sample.jetpackcompose.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExerciseProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        Toolbar()
                        Greeting("Android")
                        ContentList()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExerciseProjectTheme {
        Column {
            Toolbar()
            Greeting("Android")
            ContentList()
        }
    }
}

@Composable
fun Toolbar() {
    TopAppBar(title = { Text(text = "Popular Movies") })
}

@Composable
fun ContentList() {
    val puppies = remember { listOf(1, 2, 3, 4, 5) }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = puppies,
            itemContent = {
                MovieListItem(value = it)
            })
    }
}

@Composable
fun MovieListItem(value: Int) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "$value")
        }
    }
}
