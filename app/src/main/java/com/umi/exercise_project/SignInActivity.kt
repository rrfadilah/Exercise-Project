package com.umi.exercise_project


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umi.exercise_project.model.Biodata
import com.umi.exercise_project.model.UserInfo

class SignInActivity : AppCompatActivity() {

    companion object {
        const val KEY = "KEY"
    }

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // untuk menerima intent explicit
        val valueFromAnotherActivity = intent.getStringExtra(Constant.Intent.EMAIL)

        //untuk menerima intent bundle
        intent.extras?.getString(Constant.Intent.PHONE)

        //untuk menerima intent Serializeble
        intent.extras?.getSerializable(Constant.Serialize.KEY) as Biodata

        //untuk menerima intent Parcelable
        intent.extras?.getParcelable<UserInfo>(Constant.Parcelize.KEY)
    }
}