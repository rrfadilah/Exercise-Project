package com.rizky.exercise_project.ui._sample.jetpackcompose.homepage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.R
import com.rizky.exercise_project.database.MyDoctorDatabase
import com.rizky.exercise_project.ui._sample.jetpackcompose.homepage.ui.theme.ExerciseProjectTheme
import com.rizky.exercise_project.ui.home.doctor.DoctorViewModel


class HomeActivity : ComponentActivity() {

    internal val viewModel: DoctorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = MyDoctorDatabase.getInstance(this)
        val pref = this.getSharedPreferences(
            Constant.Preferences.PREF_NAME,
            AppCompatActivity.MODE_PRIVATE
        )
        viewModel.onViewLoaded(db = db, preferences = pref)

        setContent {
            ExerciseProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting2("Android")
                    Profile(viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ExerciseProjectTheme {
        Column {
        }
    }
}

@Composable
fun Profile(viewModel: DoctorViewModel) {
    val profile = viewModel.shouldShowProfile.observeAsState().value
    Row {
        Image(
            painter = painterResource(id = R.drawable.img_user_1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(RoundedCornerShape(corner = CornerSize(50.dp)))
        )
        Column {
            Text(text = profile?.name.orEmpty(), modifier = Modifier.size(20.dp))
            Text(text = profile?.job.orEmpty())
        }
    }
}