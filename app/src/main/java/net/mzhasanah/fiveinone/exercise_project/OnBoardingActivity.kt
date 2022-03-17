package net.mzhasanah.fiveinone.exercise_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        Log.d("Lifecycle", "Lifecycle OnCreate")
    }

    fun ClickSignIn(V: View?) {
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "Lifecycle OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "Lifecycle OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "Lifecycle OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "Lifecycle OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "Lifecycle OnDestroy")
    }
}