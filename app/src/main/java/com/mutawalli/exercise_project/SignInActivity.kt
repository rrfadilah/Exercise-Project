package com.mutawalli.exercise_project

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.google.android.material.snackbar.Snackbar
import com.mutawalli.exercise_project.databinding.ActivitySignInBinding
import com.mutawalli.exercise_project.model.Biodata
import com.mutawalli.exercise_project.model.UserInfo

class SignInActivity : AppCompatActivity() {
    companion object {
        const val KEY = "KEY"
    }

    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)
        binding.btnSignIn.setOnClickListener {
            sharedPreferences(pref)
        }

        // Untuk menerima intent explicit
//        val valueFromAnotherActivity = intent.getStringExtra(Constant.Intent.EMAIL)

        // Untuk menerima intent bundle
//        intent.extras?.getString(Constant.Intent.PHONE)

        // Untuk menerima intent serializable
//        intent.extras?.getSerializable(Constant.Serialize.KEY) as Biodata

        // Untuk menerima intent parcelize
//        intent.extras?.getParcelable<UserInfo>(Constant.Parcelize.KEY)

    }

    fun openPage() {
        Snackbar.make(binding.root, "Membuka Halaman Sign In", Snackbar.LENGTH_INDEFINITE)
            .setAction("Klik Disini") {
                // aksi yang akan kita jalan kan ketika klik di action nya...
                Toast.makeText(this, "Membuka halaman Sign In", Toast.LENGTH_LONG).show()
            }.show()
    }

    fun dialogStandard() {
        // Dialog standard
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("judul dialog")
        dialog.setMessage("isi pesan dialog")
        dialog.setCancelable(true)
        dialog.show()
    }

    fun dialogAction() {
        // dialog dengan action
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("judul dialog")
        dialog.setMessage("isi pesan dialog")
        dialog.setPositiveButton("Positif", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.dismiss()
            }
        })
        dialog.setNegativeButton("Negatif") { dialog, which ->
            dialog.dismiss()
        }
        dialog.setNeutralButton("Netral") { dialog, which ->
            dialog.dismiss()
        }

        dialog.show()
    }

    fun dialogCustomLayout() {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_custom, null, false)
        val dialog = AlertDialog.Builder(this)
        dialog.setView(view)
        dialog.create().show()
    }

    fun dialogWithFragment() {
        val dialog = CustomDialogFragment()
        dialog.show(supportFragmentManager, null)
    }

    // contoh pengguanan sharedpreferences di dalam sign in
    fun sharedPreferences(pref: SharedPreferences) {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        // untuk melakukan penyimpanan ke sharedpreferences hanya mengguanakn code berikut ini
        pref.edit {
            putString(Constant.Preferences.KEY.EMAIL, email)
            putString(Constant.Preferences.KEY.PASSWORD, password)
            apply()
        }

        // untuk mengambil data dari shared preferences menggunakan code berikut ini
        val prefEmail = pref.getString(Constant.Preferences.KEY.EMAIL, "")
        val prefPassword = pref.getString(Constant.Preferences.KEY.PASSWORD, "")
        Snackbar.make(
            binding.root,
            "Value yang tersimpan adalah berikut: Email: $prefEmail dan Password: $prefPassword",
            Snackbar.LENGTH_INDEFINITE
        ).show()
    }

    // contoh satu lagi pengguanaan shared preferences untuk konfigurasi aplikasi
    fun sharedPrefDarkMode(pref: SharedPreferences, darkMode: Boolean) {
        pref.edit {
            putBoolean(Constant.Preferences.KEY.DARK_MODE, darkMode)
        }
        pref.getBoolean(Constant.Preferences.KEY.DARK_MODE, false)
    }

    // contoh satu lagi pengguanaan shared preferences untuk konfigurasi aplikasi
    fun sharedPrefLanguage(pref: SharedPreferences, language: String) {
        pref.edit {
            putString(Constant.Preferences.KEY.APP_LANGUAGE, language)
        }
        pref.getString(Constant.Preferences.KEY.APP_LANGUAGE, "id")
    }
}