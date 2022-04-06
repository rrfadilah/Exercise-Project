package com.tegarpenemuan.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tegarpenemuan.myapplication.databinding.ActivitySignBinding
import com.tegarpenemuan.myapplication.model.Biodata
import com.tegarpenemuan.myapplication.model.UserInfo

class SignActivity : AppCompatActivity() {

    companion object {
        const val KEY = "KEY"
    }

    lateinit var binding: ActivitySignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvCreateNewAccount.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
        }

//        IntentData()
    }

    private fun IntentData() {
        // Untuk menerima intent explicit
        val valueFromAnotherActivity = intent.getStringExtra(Constant.Intent.EMAIL)

        // Untuk menerima intent bundle
        intent.extras?.getString(Constant.Intent.PHONE)

        // Untuk menerima intent serializable
        intent.extras?.getSerializable(Constant.Serialize.KEY) as Biodata

        // Untuk menerima intent parcelize
        intent.extras?.getParcelable<UserInfo>(Constant.Parcelize.KEY)
    }
}