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
import com.rizky.exercise_project.databinding.ActivityRegisterFormBinding

class RegisterFormActivity : AppCompatActivity() {

    companion object {
        const val INTENT_EMAIL = "formEmail"
        const val INTENT_PASSWORD = "formPassword"
    }

    lateinit var binding: ActivityRegisterFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterFormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)

        val bundle = intent.extras
        val loginEmail = bundle?.getString(INTENT_EMAIL)
        val loginPassword = bundle?.getString(INTENT_PASSWORD)

        if (bundle != null) {
            binding.formEmail.setText(loginEmail)
            binding.formPassword.setText(loginPassword)
        }

        // intent data juga bisa kita store di Constant, lalu dipanggil seperti dibawah
        intent.extras?.getString(Constant.Intent.KEY)

        binding.btnContinue.setOnClickListener {

            val formFullName = binding.formFullName.text.toString()
            val formPekerjaan = binding.formPekerjaan.text.toString()
            val formEmail = binding.formEmail.text.toString()
            val formPassword = binding.formPassword.text.toString()

            // Variabel untuk Validator
            val passwordCombine = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$"

            // Variabel untuk Preferences
            val prefData = Constant.Preferences.KEY
            // variabel untuk validasi email agar tidak kembar
            val prefEmail = pref.getString(prefData.PREF_EMAIL, "")


            if (formEmail.trim().isEmpty() && formPassword.trim().isEmpty()) {
                dialogActivity(
                    "Kolom Email & Password Kosong",
                    "Kolom email dan kolom password adalah wajib. Mohon isi keduanya."
                )
            } else if (formEmail.trim().isEmpty()) {
                dialogActivity(
                    "Kolom Email Kosong",
                    "Kolom email wajib diisi"
                )
            } else if (!isValidEmail(formEmail.trim())) {
                dialogActivity(
                    "Email Salah",
                    "Mohon isikan email dengan benar"
                )
            } else if (formPassword.trim().isEmpty()) {
                dialogActivity(
                    "Kolom Password Kosong",
                    "Kolom password wajib diisi"
                )
            } else if (formPassword.trim().length < 8) {
                dialogCustomLayout()
            } else if (!formPassword.trim().matches(Regex(passwordCombine))) {
                dialogWithFragment()
            } else {
                if (formEmail == prefEmail) {
                    Toast.makeText(this, "Email sudah dipakai", Toast.LENGTH_SHORT).show()
                } else {

                    pref.edit {
                        putString(prefData.PREF_FULLNAME, formFullName)
                        putString(prefData.PREF_PEKERJAAN, formPekerjaan)
                        putString(prefData.PREF_EMAIL, formEmail)
                        putString(prefData.PREF_PASSWORD, formPassword)
                        apply()
                    }

                    val prefFullName = pref.getString(prefData.PREF_FULLNAME, "")
                    val prefPekerjaan = pref.getString(prefData.PREF_PEKERJAAN, "")
                    val prefEmail = pref.getString(prefData.PREF_EMAIL, "")
                    val prefPassword = pref.getString(prefData.PREF_PASSWORD, "")

                    dialogActivity(
                        "Pembuatan akun berhasil", "Pembuakan akun berhasil," +
                                "\nNama: $prefFullName" +
                                "\nPekerjaan: $prefPekerjaan" +
                                "\nEmail: $prefEmail" +
                                "\nPassword: $prefPassword" +
                                "\n\nTekan OK untuk melanjutkan login."
                    )
                }
            }
        }
    }

    /* Dialog */
    private fun dialogActivity(title: String, message: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(title)
        dialog.setMessage(message)
        dialog.setPositiveButton("OK", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                val intentLogin = Intent(this@RegisterFormActivity, LoginFormActivity::class.java)
                startActivity(intentLogin)
            }
        })
        dialog.show()
    }

    /* Validator */
    private fun isValidEmail(target: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    /* Dialog Custom Layout */
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
}