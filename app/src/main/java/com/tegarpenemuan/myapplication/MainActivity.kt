package com.tegarpenemuan.myapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.content.edit
import com.tegarpenemuan.myapplication.databinding.ActivityMainBinding
import com.tegarpenemuan.myapplication.galleryfragment.GalleryActivity
import com.tegarpenemuan.myapplication.home.ui.HomeActivity
import com.tegarpenemuan.myapplication.sampleFragment.PicturesActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val registerPreferences =
            this.getSharedPreferences(Constant.Register.PREF_REGISTER_NAME, MODE_PRIVATE)

        Handler(Looper.getMainLooper()).postDelayed({
            if(!registerPreferences.contains(Constant.Register.KEY.NAMA)) {
                val intent = Intent(this, OnBoardingActivity::class.java)
                startActivity(intent)
            } else {
                if(!registerPreferences.getBoolean(Constant.Register.KEY.LOGIN,false)) {
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                } else {
                    registerPreferences.edit {
                        putBoolean(Constant.Register.KEY.LOGIN, true)
                        apply()
                    }
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
            }
        }, 3000)

        Log.d("Lifecycle","Lifecycle OnCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle","Lifecycle OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle","Lifecycle OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle","Lifecycle OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle","Lifecycle OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle","Lifecycle OnDestroy")
    }
}