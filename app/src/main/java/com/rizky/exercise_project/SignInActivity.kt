package com.rizky.exercise_project

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.rizky.exercise_project.databinding.ActivitySignInBinding
import com.rizky.exercise_project.model.Biodata
import com.rizky.exercise_project.model.UserInfo

class SignInActivity : AppCompatActivity() {
    companion object {
        const val KEY = "KEY"
    }

    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Untuk menerima intent explicit
//        val valueFromAnotherActivity = intent.getStringExtra(Constant.Intent.EMAIL)

        // Untuk menerima intent bundle
//        intent.extras?.getString(Constant.Intent.PHONE)

        // Untuk menerima intent serializable
//        intent.extras?.getSerializable(Constant.Serialize.KEY) as Biodata

        // Untuk menerima intent parcelize
//        intent.extras?.getParcelable<UserInfo>(Constant.Parcelize.KEY)


        openPage()
    }

    fun openPage() {
        Snackbar.make(binding.root, "Membuka Halaman Sign In", Snackbar.LENGTH_INDEFINITE)
            .setAction("Klik Disini") {
                // aksi yang akan kita jalan kan ketika klik di action nya...
                Toast.makeText(this, "Membuka halaman Sign In", Toast.LENGTH_LONG).show()
            }.show()
    }
}