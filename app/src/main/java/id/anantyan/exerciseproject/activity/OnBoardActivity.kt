package id.anantyan.exerciseproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.databinding.ActivityOnBoardBinding
import id.anantyan.exerciseproject.utils.viewbinding.viewBinding

class OnBoardActivity : AppCompatActivity() {

    private val binding: ActivityOnBoardBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board)
        onBinding()
        Log.d("CREATE", "onCreate")
    }

    private fun onBinding() {
        binding.btnSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
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