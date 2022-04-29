package com.example.exercise_project.ui.signin

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.doAfterTextChanged
import com.example.exercise_project.Constant
import com.example.exercise_project.CustomDialogFragment
import com.example.exercise_project.R
import com.example.exercise_project.databinding.ActivitySignBinding
import com.example.exercise_project.ui.home.HomeActivity
import com.example.exercise_project.ui.home.database.MyDoctorDatabase
import com.example.exercise_project.ui.signup.SignUpActivity
import com.google.android.material.snackbar.Snackbar

class SignActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignBinding
    private val viewModel: SignInViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val mail = intent.getStringExtra("email")
////        val password = intent.getStringExtra("password")
//        edtmail.setText(mail)
//        edtpassword.setText(password)

        val db = MyDoctorDatabase.getInstance(this.applicationContext)
        val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)
        viewModel.onViewLoaded(db, pref)

        bindViewModel()
        bindView()

        //get text email and password
//        val edtmail = findViewById<EditText>(R.id.et_email)
//        val edtpassword = findViewById<EditText>(R.id.et_password)
//        val registerPreferences =
//            this.getSharedPreferences(Constant.Register.PREF_REGISTER_NAME, MODE_PRIVATE)
//        val btnlogin = findViewById<TextView>(R.id.btn_sign_in)

//        btnlogin.setOnClickListener {
//            val email = edtmail.text.toString()
//            val password = edtpassword.text.toString()
//            if (email.isEmpty() || password.isEmpty()) {
//                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
//            } else if (email.isEmpty()) {
//                Toast.makeText(this, R.string.email_kosong, Toast.LENGTH_SHORT).show()
//            } else if (password.isEmpty()) {
//                Toast.makeText(this, R.string.password_kosong, Toast.LENGTH_SHORT).show()
//            } else if (password.length < 8) {
//                Toast.makeText(this, R.string.password_character, Toast.LENGTH_SHORT).show()
//            } else if (!password.matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
//                Toast.makeText(this, R.string.password_lower_upper_case, Toast.LENGTH_SHORT).show()
//            } else if (!email
//                    .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))
//            ) {
//                Toast.makeText(this, R.string.email_salah, Toast.LENGTH_SHORT).show()
//            } else {
//                val emailPreferences =
//                    registerPreferences.getString(Constant.Register.KEY.EMAIL, "")
//                val passwordPreferences =
//                    registerPreferences.getString(Constant.Register.KEY.PASSWORD, "")
//                if (email == emailPreferences && password == passwordPreferences) {
//                    val intent = Intent(this, HomeActivity::class.java)
//                    startActivity(intent)
//                } else {
//                    Snackbar.make(
//                        it, "Email or password is incorrect",
//                        Snackbar.LENGTH_LONG
//                    ).show()
//                }
//            }
//        }
        val btnRegister = findViewById<TextView>(R.id.tv_create_new_account)
        btnRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


//        btnlogin.setOnClickListener {
//            if (edtmail.text.toString().isEmpty()) {
//                Toast.makeText(this, R.string.email_kosong, Toast.LENGTH_SHORT).show()
//            } else if (edtpassword.text.toString().isEmpty()) {
//                Toast.makeText(this, R.string.password_kosong, Toast.LENGTH_SHORT).show()
//            } else if (edtpassword.text.toString().length < 8) {
//                Toast.makeText(this, R.string.password_character, Toast.LENGTH_SHORT).show()
//            } else if (!edtpassword.text.toString().matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
//                Toast.makeText(this, R.string.password_lower_upper_case, Toast.LENGTH_SHORT).show()
//            } else if (!edtmail.text.toString()
//                    .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))
//            ) {
//                Toast.makeText(this, R.string.email_salah, Toast.LENGTH_SHORT).show()
//            } else {
//                openPage()
//            }
//        }

//        btnlogin.setOnClickListener {
//            if (edtmail.text.toString().isEmpty() || edtpassword.text.toString().isEmpty()) {
//                dialogEmailPasswordKosong()
//                dialogActionEmailPasswordKosong()
//                dialogEmailPasswordCustomLayoutKosong()
//                dialogEmailPasswordFragmentKosong()
//            } else if (!edtpassword.text.toString().matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+")) || !edtmail.text.toString()
//                    .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$")) || edtpassword.text.toString().length < 8
//            ) {
//                dialogEmailPasswordValidation()
//                dialogActionEmailPasswordValidation()
//                dialogEmailPasswordCustomLayoutValidation()
//                dialogEmailPasswordFragmentValidation()
//
//            } else {
//                openPage()
//            }
//
//
//        }

//        val btnCreateNewAccount = findViewById<TextView>(R.id.tv_create_new_account)
//        btnCreateNewAccount.setOnClickListener {
//            Toast.makeText(this, "Tombol create new account di pencet", Toast.LENGTH_SHORT).show()
//        }
//        val btnForgotPassword = findViewById<TextView>(R.id.tv_forgot_password)
//        btnForgotPassword.setOnClickListener {
//            Toast.makeText(this, "Tombol forgot password di pencet", Toast.LENGTH_SHORT).show()
//        }
//
//        val dialog1 = AlertDialog.Builder(this)
//        dialog1.setTitle("judul dialog")
//        dialog1.setMessage("isi pesan dialog")
//        dialog1.setCancelable(true)
//        dialog1.show()
//
//        val dialogAction = AlertDialog.Builder(this)
//        dialogAction.setTitle("judul dialog")
//        dialogAction.setMessage("konfirmasi keluar?")
//        //menggunakan object DialogInterface.OnClickListener
//        dialogAction.setPositiveButton("ya", object : DialogInterface.OnClickListener {
//            override fun onClick(dialog: DialogInterface?, which: Int) {
//                dialog?.dismiss()
//            }
//        })
//        //menggunakan lambda
//        dialogAction.setNegativeButton("tidak") { dialog, which ->
//            dialog?.dismiss()
//        }
//        dialogAction.setNeutralButton("netral") { dialog, which ->
//            dialog?.dismiss()
//        }
//        dialogAction.show()
//        dialogCustomLayout()
//        dialogWithFragment()
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


    //make function when back button pressed then exit the app
    override fun onBackPressed() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Konfirmasi")
        dialog.setMessage("Apakah anda ingin keluar?")
        dialog.setPositiveButton("Ya") { dialog, which ->
            moveTaskToBack(true)
        }
        dialog.setNegativeButton("Tidak") { dialog, which ->
            dialog?.dismiss()
        }
        dialog.show()
    }
//
//    private fun dialogCustomLayout() {
//        val view = layoutInflater.inflate(R.layout.dialog_custom, null, false)
//        val dialog = AlertDialog.Builder(this)
//        dialog.setView(view)
//        dialog.setCancelable(true)
//        dialog.show()
//    }
//
//    private fun dialogWithFragment() {
//        val dialog = CustomDialogFragment()
//        dialog.show(supportFragmentManager, null)
//    }
//
//    private fun openPage() {
//
//        Toast.makeText(this, "Membuka halaman Sign In", Toast.LENGTH_LONG).show()
//        val snackbar = Snackbar.make(
//            findViewById(R.id.cl_root),
//            "Membuka halaman Sign In",
//            Snackbar.LENGTH_LONG
//        )
//        snackbar.show()
//    }
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        Toast.makeText(this, "Tombol back di pencet", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun dialogActionEmailPasswordValidation() {
//        val dialog = AlertDialog.Builder(this)
//        dialog.setTitle("email dan password tidak valid")
//        dialog.setMessage("Apakah anda yakin email dan password sudah benar?")
//        dialog.setCancelable(true)
//        dialog.setPositiveButton("ya") { dialog, which ->
//            dialog?.dismiss()
//        }
//        dialog.setNegativeButton("tidak") { dialog, which ->
//            dialog?.dismiss()
//        }
//        dialog.show()
//    }
//
//    private fun dialogEmailPasswordValidation() {
//        val dialog = AlertDialog.Builder(this)
//        dialog.setTitle("email dan password tidak valid")
//        dialog.setMessage("email dan password tidak valid")
//        dialog.setCancelable(true)
//        dialog.show()
//    }
//
//    private fun dialogEmailPasswordFragmentValidation() {
//        val dialog = EmailDanPasswordValidation()
//        dialog.show(supportFragmentManager, null)
//    }
//
//    private fun dialogEmailPasswordCustomLayoutValidation() {
//        val view = layoutInflater.inflate(R.layout.dialog_email_password, null, false)
//        val dialog = AlertDialog.Builder(this)
//        dialog.setView(view)
//        dialog.setCancelable(true)
//        dialog.show()
//    }
//
//    private fun dialogEmailPasswordKosong() {
//        val dialog = AlertDialog.Builder(this)
//        dialog.setTitle("email dan password kosong")
//        dialog.setMessage("email dan password kosong")
//        dialog.setCancelable(true)
//        dialog.show()
//    }
//
//    private fun dialogActionEmailPasswordKosong() {
//        val dialog = AlertDialog.Builder(this)
//        dialog.setTitle("email dan password kosong")
//        dialog.setMessage("email dan password kosong, isi terlebih dahulu")
//        dialog.setCancelable(true)
//        dialog.setPositiveButton("oke") { dialog, which ->
//            dialog?.dismiss()
//        }
//        dialog.setNegativeButton("tidak") { dialog, which ->
//            dialog?.dismiss()
//        }
//        dialog.show()
//    }
//
//    fun dialogEmailPasswordFragmentKosong() {
//        val dialog = DialogEmailDanPasswordKosong()
//        dialog.show(supportFragmentManager, null)
//    }
//
//    fun dialogEmailPasswordCustomLayoutKosong() {
//        val view = layoutInflater.inflate(R.layout.dialog_email_password_kosong, null, false)
//        val dialog = AlertDialog.Builder(this)
//        dialog.setView(view)
//        dialog.setCancelable(true)
//        dialog.show()
//    }
}