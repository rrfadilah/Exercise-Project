package com.tegarpenemuan.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.android.material.snackbar.Snackbar
import com.tegarpenemuan.myapplication.database.MyDoctorDatabase
import com.tegarpenemuan.myapplication.databinding.ActivitySignBinding
import com.tegarpenemuan.myapplication.home.ui.HomeActivity
import com.tegarpenemuan.myapplication.model.Biodata
import com.tegarpenemuan.myapplication.model.UserInfo
import com.tegarpenemuan.myapplication.utils.showCustomToast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {

    companion object {
        const val KEY = "KEY"
    }

    lateinit var binding: ActivitySignBinding
    private var db: MyDoctorDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MyDoctorDatabase.getInstance(this.applicationContext)

        val registerPreferences =
            this.getSharedPreferences(Constant.Register.PREF_REGISTER_NAME, MODE_PRIVATE)
        val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)

        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            getUser(email, password)

            // dialogCustomLayout()
//            validasiFormToastSnack(registerPreferences)
//            validasiFormDialog()
            sharedPreferences(pref)
        }

        binding.tvCreateNewAccount.setOnClickListener {
//            Toast(this).showCustomToast("Tombol create new accoiunt di pencet", this)
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.tvForgotPassword.setOnClickListener {
            Toast(this).showCustomToast("Forgot Password Clicked", this)
        }

    }

    fun getUser(email: String, password: String) {
        GlobalScope.launch {
            val user = db?.userDAO()?.getUser(email = email, password = password)
            runOnUiThread {
                user?.let {
                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                } ?: run {
                    Toast.makeText(this@SignInActivity, "User tidak ditemukan", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
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
        if (pref.contains(Constant.Register.KEY.NAMA)) {
            val prefEmail = pref.getString(Constant.Preferences.KEY.EMAIL, "")
            val prefPassword = pref.getString(Constant.Preferences.KEY.PASSWORD, "")
            Snackbar.make(
                binding.root,
                "Value yang tersimpan adalah berikut: Email: $prefEmail dan Password: $prefPassword",
                Snackbar.LENGTH_INDEFINITE
            ).show()
        }
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

    private fun validasiFormDialog() {
        if (binding.etEmail.text.isEmpty() && binding.etPassword.text.isEmpty()) {
            dialogAction("Peringatan", "Email dan Password tidak boleh kosong")
        } else if (binding.etEmail.text.isEmpty()) {
            dialogStandard("Peringatan", "Email Tidak Boleh Kosnong")
        } else if (!binding.etEmail.text.matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))
        ) {
            dialogWithFragment()
        } else if (binding.etPassword.text.isEmpty()) {
            dialogStandard("Peringatan", "Password Tidak Boleh Kosnong")
        } else if (binding.etPassword.text.length < 8) {
            dialogStandard("Peringatan", "Password harus lebih dari 8 karakter")
        } else if (!binding.etPassword.text.matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
            dialogStandard("Peringatan", "Password harus mengandung upper case dan lowercase")
        } else {
            dialogCustomLayout()
        }
    }

    fun dialogStandard(title: String, message: String) {
        // Dialog standard
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(title)
        dialog.setMessage(message)
        dialog.setCancelable(true)
        dialog.show()
    }

    fun dialogAction(title: String, message: String) {
        // dialog dengan action
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(title)
        dialog.setMessage(message)
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

    private fun validasiFormToastSnack(pref: SharedPreferences) {
        if (binding.etEmail.text.isEmpty()) {
            Toast(this).showCustomToast("Email tidak boleh kosong", this)
        } else if (!binding.etEmail.text.matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))
        ) {
            Toast(this).showCustomToast("Email Tidak Valid", this)
        } else if (binding.etPassword.text.isEmpty()) {
            Toast(this).showCustomToast("Password tidak boleh kosong", this)
        } else if (binding.etPassword.text.length < 8) {
            Toast(this).showCustomToast("Password harus lebih dari 8 karakter", this)
        } else if (!binding.etPassword.text.matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
            Toast(this).showCustomToast("Password harus mengandung upper case dan lowercase", this)
        } else {
            val emailPreferences =
                pref.getString(Constant.Register.KEY.EMAIL, "")
            val passwordPreferences =
                pref.getString(Constant.Register.KEY.PASSWORD, "")
            if (binding.etEmail.text.toString() == emailPreferences && binding.etPassword.text.toString() == passwordPreferences) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                val registerPreferences =
                    this.getSharedPreferences(Constant.Register.PREF_REGISTER_NAME, MODE_PRIVATE)
                val editor = registerPreferences.edit()
                editor.putBoolean(Constant.Register.KEY.LOGIN, true)
                editor.apply()
                finish()
            } else {
                Toast.makeText(this, "Anda Belum Memiliki Akun", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun openPage() {
        Snackbar.make(binding.root, "Selamat Anda Berhasil Login", Snackbar.LENGTH_INDEFINITE)
            .setAction("Logout") {
                Toast.makeText(this, "Anda Sudah Logout", Toast.LENGTH_LONG).show()
            }.show()
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

    override fun onStop() {
        super.onStop()
        finish()
//        startActivity(Intent(this,MainActivity::class.java))
//        Toast(this).showCustomToast("Close Sign In", this)
    }
}