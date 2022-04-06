package net.mzhasanah.fiveinone.exerciseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import net.mzhasanah.fiveinone.exerciseproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val handler = Handler()
        handler.postDelayed({
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }, 5000L)
    }
}