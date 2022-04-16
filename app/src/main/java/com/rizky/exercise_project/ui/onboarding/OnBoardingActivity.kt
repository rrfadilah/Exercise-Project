package com.rizky.exercise_project.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.ui.signin.SignInActivity
import com.rizky.exercise_project.databinding.ActivityOnBoardingBinding
import com.rizky.exercise_project.home.HomeActivity
import com.rizky.exercise_project.model.Biodata
import com.rizky.exercise_project.model.UserInfo
import com.rizky.exercise_project.ui.signup.SignUpActivity

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }

    fun intentExplicit() {
        // Contoh Pengguanaan explisit
        val intent = Intent(this, SignInActivity::class.java).apply {
            putExtra(Constant.Intent.EMAIL, "value")
        }
        startActivity(intent)
    }

    fun intentImplicit() {
        // Contoh Penggunaan impicit
        val intent = Intent(this, SignInActivity::class.java).apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "VALUE")
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun intentBundle() {
        val intent = Intent(this, SignInActivity::class.java)
        val bundle = Bundle()

        bundle.putString(Constant.Intent.PHONE, "VALUE")
        bundle.putString(Constant.Intent.EMAIL, "VALUE")
        bundle.putString(Constant.Intent.KEY, "VALUE")
        intent.putExtras(bundle)

        startActivity(intent)
    }

    fun intentSerialize() {
        val intent = Intent(this, SignInActivity::class.java)
        val biodata = Biodata(
            key = "VALUE_KEY",
            phone = "VALUE_PHONE",
            email = "VALUE_EMAIL"
        )

        intent.putExtra(Constant.Serialize.KEY, biodata)
        startActivity(intent)
    }

    fun intentParcelable() {
        val intent = Intent(this, SignInActivity::class.java)
        val userInfo = UserInfo(
            key = "VALUE_KEY",
            phone = "VALUE_PHONE",
            email = "VALUE_EMAIL"
        )

        intent.putExtra(Constant.Parcelize.KEY, userInfo)
        startActivity(intent)
    }


}