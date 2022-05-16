package com.dzamir.exercise_project.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dzamir.exercise_project.Constant
import com.dzamir.exercise_project.ui.signin.OnSigninActivity
import com.dzamir.exercise_project.databinding.ActivityOnBoardingBinding
import com.dzamir.exercise_project.model.Biodata
import com.dzamir.exercise_project.model.UserInfo

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnSignIn.setOnClickListener{
//            val intent = Intent(this, OnSigninActivity::class.java)
//        }
        hello()
    }

    fun hello(){
        binding.btnSignIn.setOnClickListener {
            val intent = Intent(this, OnSigninActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    fun intenExplicit(){
        // Penggunaan Explisit
        val intent = Intent(this, OnSigninActivity::class.java).apply {
            putExtra(Constant.Intent.EMAIL, "value")
        }
        startActivity(intent)
    }

    fun intentImplicit(){
        // Penggunaan implicit
        val intent = Intent(this, OnSigninActivity::class.java).apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "VALUE")
        }
        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    fun intentBundle(){
        val intent = Intent(this, OnSigninActivity::class.java)
        val bundle = Bundle()

        bundle.putString(Constant.Intent.PHONE, "VALUE")
        intent.putExtras(bundle)

        startActivity(intent)
    }

    fun intentSerialize(){
        val intent = Intent(this, OnSigninActivity::class.java)

        val biodata = Biodata(
            key = "VALUE_KEY",
            phone = "VALUE_PHONE",
            email = "VALUE_EMAIL"
        )

        intent.putExtra(Constant.Serialize.KEY, biodata)
        startActivity(intent)
    }

    fun intentParcelable(){
        val intent = Intent(this, OnSigninActivity::class.java)

        val userInfo = UserInfo(
            key = "VALUE_KEY",
            phone = "VALUE_PHONE",
            email = "VALUE_EMAIL"
        )

        intent.putExtra(Constant.Parcelize.KEY, userInfo)
        startActivity(intent)
    }
}