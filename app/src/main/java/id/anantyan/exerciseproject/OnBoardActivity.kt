package id.anantyan.exerciseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class OnBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board)
        Log.d("CREATE", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("START", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("RESUME", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("PAUSE", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("STOP", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DESTROY", "onDestroy")
    }
}