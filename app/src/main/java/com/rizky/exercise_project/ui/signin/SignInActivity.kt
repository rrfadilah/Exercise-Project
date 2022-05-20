package com.rizky.exercise_project.ui.signin

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.CustomDialogFragment
import com.rizky.exercise_project.R
import com.rizky.exercise_project.database.MyDoctorDatabase
import com.rizky.exercise_project.databinding.ActivitySignInBinding
import com.rizky.exercise_project.datastore.AuthDataStoreManager
import com.rizky.exercise_project.repository.AuthRepository
import com.rizky.exercise_project.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = MyDoctorDatabase.getInstance(this.applicationContext)
        val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)
        viewModel.onViewLoaded(db, pref)

        bindViewModel()
        bindView()
    }

    private fun bindViewModel() {
        viewModel.shouldShowError.observe(this) {
            val snackbar = Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
            snackbar.view.setBackgroundColor(Color.RED)
            snackbar.show()
        }

        viewModel.shouldOpenHomePage.observe(this) {
            if (it) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    private fun bindView() {
        binding.etEmail.doAfterTextChanged {
            viewModel.onChangeEmail(it.toString())
        }

        binding.etPassword.doAfterTextChanged {
            viewModel.onChangePassword(it.toString())
        }

        binding.btnSignIn.setOnClickListener {
            viewModel.onClickSignIn()
        }
    }


    // not used area
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
        dialog.setPositiveButton("POsitif", object : DialogInterface.OnClickListener {
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

//    fun getUser(email: String, password: String) {
//        GlobalScope.launch {
//            val user = db?.userDAO()?.getUser(email = email, password = password)
//            runOnUiThread {
//                user?.let {
//                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
//                } ?: run {
//                    Toast.makeText(this@SignInActivity, "User tidak ditemukan", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//        }
//    }
}