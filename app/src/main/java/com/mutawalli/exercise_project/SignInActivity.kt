package com.mutawalli.exercise_project

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mutawalli.exercise_project.databinding.ActivitySignInBinding
import com.mutawalli.exercise_project.home.HomeActivity
import com.mutawalli.exercise_project.model.Biodata
import com.mutawalli.exercise_project.model.UserInfo
import java.net.InterfaceAddress

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    companion object {
        const val KEY = "KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Untuk menerima intent explicit
//        val valueFromAnotherActivity = intent.getStringExtra(Constant.Intent.EMAIL)

        // Untuk menerima intent bundle
//        intent.extras?.getString(Constant.Intent.PHONE)

        // Untuk menerima intent serializable
//        intent.extras?.getSerializable(Constant.Serialize.KEY) as Biodata

        // Untuk menerima intent parcelize
//        intent.extras?.getParcelable<UserInfo>(Constant.Parcelize.KEY)

        binding.tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Lupa Password di pencet", Toast.LENGTH_SHORT).show()
        }

        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val pass = binding.etPassword.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "Kolom email masih kosong", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email tidak valid", Toast.LENGTH_SHORT).show()
            } else if (pass.isEmpty()) {
                Toast.makeText(this, "Kolom password masih kosong", Toast.LENGTH_SHORT).show()
            } else if (pass.length < 8) {
                Toast.makeText(this, "Minimum 8 Characters", Toast.LENGTH_SHORT).show()
            } else if (!pass.matches(".*[A-Z].*".toRegex())) {
                Toast.makeText(this, "Password harus mengandung 1 Upper-case", Toast.LENGTH_SHORT)
                    .show()
            } else if (!pass.matches(".*[a-z].*".toRegex())) {
                Toast.makeText(this, "Password harus mengandung 1 Lower-case", Toast.LENGTH_SHORT)
                    .show()
            }
            return@setOnClickListener
        }

        binding.tvCreateNewAccount.setOnClickListener {
            Toast.makeText(this, "Create new account di pencet", Toast.LENGTH_SHORT).show()
        }

        openPage()

//        val dialog = AlertDialog.Builder(this)
//        dialog.setTitle("Judul Dialog")
//
//        dialog.setMessage("Isi Pesan Dialog")
//        dialog.setCancelable(true)
//        dialog.show()


        val dialogAction = AlertDialog.Builder(this)
        dialogAction.setTitle("Judul Dialog")
        dialogAction.setMessage("Pesan Dialog")
        dialogAction.setPositiveButton("Positif", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.dismiss()
            }
        })
        dialogAction.setNegativeButton("Negatif") { dialog, which ->
            dialog?.dismiss()
        }
        dialogAction.setNeutralButton("Netral") { dialog, which ->
            dialog?.dismiss()
        }
        dialogAction.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, getString(R.string.toast_text), Toast.LENGTH_LONG).show()
    }

    fun openPage() {
        Snackbar.make(binding.root, "Membuka halaman Sign In", Snackbar.LENGTH_INDEFINITE)
            .setAction("Klik Disini") {
                Toast.makeText(this, "Membuka halaman Sign In", Toast.LENGTH_LONG).show()
            }.show()
    }

}