package com.rizky.exercise_project

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import com.google.android.material.snackbar.Snackbar
import com.rizky.exercise_project.databinding.ActivityLoginFormBinding
import com.rizky.exercise_project.facebook.HomeFragment
import com.rizky.exercise_project.menu.MenuActivity

class LoginFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)

        val email = binding.formEmail.text
        val password = binding.formPassword.text

        binding.btnRegister.setOnClickListener {
            val intentRegister = Intent(this@LoginFormActivity, RegisterFormActivity::class.java)

            intentRegister.putExtra(RegisterFormActivity.INTENT_EMAIL, "$email")
            intentRegister.putExtra(RegisterFormActivity.INTENT_PASSWORD, "$password")
            startActivity(intentRegister)

            Toast.makeText(this, R.string.registerPressed, Toast.LENGTH_SHORT).show()
        }

        binding.btnSignIn.setOnClickListener {

            val passwordCombine = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$"
            val email: String = binding.formEmail.text.toString()
            val password: String = binding.formPassword.text.toString()

            if (email.trim().isEmpty() && password.trim().isEmpty()) {
                dialogActivity(
                    "Kolom Email & Password Kosong",
                    "Kolom email dan kolom password adalah wajib. Mohon isi keduanya."
                )
            } else if (email.trim().isEmpty()) {
                dialogActivity(
                    "Kolom Email Kosong",
                    "Kolom email wajib diisi"
                )
            } else if (!isValidEmail(email.trim())) {
                dialogActivity(
                    "Email Salah",
                    "Mohon isikan email dengan benar"
                )
            } else if (password.trim().isEmpty()) {
                dialogActivity(
                    "Kolom Password Kosong",
                    "Kolom password wajib diisi"
                )
            } else if (password.trim().length < 8) {
                dialogCustomLayout()
            } else if (!password.trim().matches(Regex(passwordCombine))) {
                dialogWithFragment()
            } else {
                // Shared Preferences untuk login
                val formEmail = binding.formEmail.text.toString()
                val formPassword = binding.formPassword.text.toString()

                // untuk mengambil data dari shared preferences menggunakan kode berikut
                val prefEmail = pref.getString(Constant.Preferences.KEY.PREF_EMAIL, "")
                val prefPassword = pref.getString(Constant.Preferences.KEY.PREF_PASSWORD, "")

                if ((formEmail != prefEmail) || (formPassword != prefPassword)) {
                    dialogStandard(
                        "Email atau password salah",
                        "Pastikan email dan pasword yang kamu masukkan sudah benar."
                    )
                } else {
                    val intentMenu = Intent(this@LoginFormActivity, MenuActivity::class.java)
                    startActivity(intentMenu)
                }
            }


//            if (email.trim().isEmpty()) {
//                nullEmail()
//            } else if (!isValidEmail(email.trim())) {
//                invalidEmail()
//            } else if (password.trim().isEmpty()) {
//                nullPassword()
//            } else if (password.trim().length < 8) {
//                lessPassword()
//            } else if (password.trim() != passwordCombine) {
//                passwordLowUppercase()
//            } else {
//                Toast.makeText(this, R.string.Done, Toast.LENGTH_SHORT).show()
//            }


        }

        binding.forgotPassword.setOnClickListener {
            forgotPassword()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, R.string.backPressed, Toast.LENGTH_SHORT).show()
    }


    /* Kumpulan Dialog */
    private fun dialogStandard(title: String, message: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(title)
        dialog.setMessage(message)
        dialog.show()
    }

    private fun dialogActivity(title: String, message: String) {
        val dialogAction = AlertDialog.Builder(this)
        dialogAction.setTitle(title)
        dialogAction.setMessage(message)
        dialogAction.setPositiveButton("OK", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.dismiss()
            }
        })
//        dialogAction.setNegativeButton("Negatif") { dialog, which ->
//            dialog.dismiss()
//        }
//        dialogAction.setNeutralButton("Netral") { dialog, which ->
//            dialog.dismiss()
//        }
        dialogAction.show()
    }

    private fun dialogCustomLayout() {
        val view = LayoutInflater.from(this)
            .inflate(R.layout.dialog_custom, null, false)
        val dialog = AlertDialog.Builder(this)
        dialog.setView(view)
        dialog.create().show()
    }

    private fun dialogWithFragment() {
        val dialog = CustomDialogFragment()
        dialog.show(supportFragmentManager, null)
    }

    /* Kumpulan Function Toast dan Snackbar */
    private fun openPage() {
        Toast.makeText(this, "Membuka halaman Sign In", Toast.LENGTH_SHORT).show()
        Snackbar.make(binding.root, "Membuka halaman sign in", Snackbar.LENGTH_LONG)
            .setAction("Klik Disini") {
                // aksi yang akan kita jalankan
                Toast.makeText(this, "Ini muncul kietika Snackbar diklik", Toast.LENGTH_SHORT)
                    .show()
            }.show()
    }

    // Email kosong
    private fun nullEmail() {
        Toast.makeText(this, R.string.nullEmail, Toast.LENGTH_LONG).show()
    }

    // Invalid Email
    private fun invalidEmail() {
        Toast.makeText(this, R.string.invalidEmail, Toast.LENGTH_LONG).show()
    }

    private fun isValidEmail(target: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    // Password kosong
    private fun nullPassword() {
        Toast.makeText(this, R.string.nullPassword, Toast.LENGTH_LONG).show()
    }

    // Password kurang dari 8 karakter
    private fun lessPassword() {
        Toast.makeText(this, R.string.lessPassword, Toast.LENGTH_LONG).show()
    }

    private fun isValidPassword(target: CharSequence): Boolean {
        return false
    }

    // Password tidak ada UPPERCASE dan lowercase
    private fun passwordLowUppercase() {
        Toast.makeText(this, R.string.passwordLowUppercase, Toast.LENGTH_LONG).show()
    }

    // Snackbar untuk Forgot Password
    private fun forgotPassword() {
        Snackbar.make(binding.root, R.string.forgotPassword, Snackbar.LENGTH_SHORT).show()
    }
}