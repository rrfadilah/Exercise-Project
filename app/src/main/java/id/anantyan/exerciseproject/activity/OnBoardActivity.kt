package id.anantyan.exerciseproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.anantyan.exerciseproject.databinding.ActivityOnBoardBinding
import id.anantyan.exerciseproject.utils.viewbinding.viewBinding

class OnBoardActivity : AppCompatActivity() {

    private val binding: ActivityOnBoardBinding by viewBinding()
    private val onBinding: () -> Unit = {
        binding.btnSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnSignUp.setOnClickListener {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBinding()
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