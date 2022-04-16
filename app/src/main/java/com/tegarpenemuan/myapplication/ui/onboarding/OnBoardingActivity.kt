package com.tegarpenemuan.myapplication.ui.onboarding

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.afollestad.viewpagerdots.DotsIndicator
import com.tegarpenemuan.myapplication.*
import com.tegarpenemuan.myapplication.databinding.ActivityOnBoardingBinding
import com.tegarpenemuan.myapplication.model.Biodata
import com.tegarpenemuan.myapplication.model.UserInfo
import com.tegarpenemuan.myapplication.ui.signin.SignInActivity
import com.tegarpenemuan.myapplication.ui.signup.SignUpActivity

class OnBoardingActivity : AppCompatActivity() {

    lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
//            Toast(this).showCustomToast("Maaf, Login Terlebih Dahulu", this)
        }

        onBoarding()
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

    private fun onBoarding() {
        val imgs = listOf(
            R.drawable.bg_onboarding,
            R.drawable.bg_onboarding,
            R.drawable.bg_onboarding
        )

        val adapter = OnBoardingAdapter(imgs, this)
        val dots: DotsIndicator = findViewById(R.id.dots)
        val viewPager = findViewById<ViewPager>(R.id.vp_on_boarding)
        viewPager.adapter = adapter
        dots.attachViewPager(viewPager)
        dots.setDotTint(Color.WHITE)
    }
}