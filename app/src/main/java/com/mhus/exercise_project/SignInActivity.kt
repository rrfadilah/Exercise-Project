package com.mhus.exercise_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mhus.exercise_project.model.Biodata
import com.mhus.exercise_project.model.UserInfo

class SignInActivity : AppCompatActivity() {
    companion object {
        const val KEY = "KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

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