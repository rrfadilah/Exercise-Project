package com.mhus.exercise_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mhus.exercise_project.databinding.ActivityMainBinding
import com.mhus.exercise_project.databinding.ActivityOnBoardingBinding
import com.mhus.exercise_project.galeryfragment.AdapterGallery
import com.mhus.exercise_project.home.HomeActivity
import com.mhus.exercise_project.model.Biodata
import com.mhus.exercise_project.model.UserInfo

class OnBoardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listGallery = listOf<Fragment>(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment(),
            FourthFragment()
        )

        val adapter = AdapterGallery(supportFragmentManager, listGallery)
        binding.vpOnboarding.adapter = adapter

        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
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