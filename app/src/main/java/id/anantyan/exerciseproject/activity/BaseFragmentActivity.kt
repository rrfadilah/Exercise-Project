package id.anantyan.exerciseproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.anantyan.exerciseproject.R

class BaseFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_fragment)
    }
}