package com.example.mydoctor.ui.signin

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.mydoctor.Constant
import com.example.mydoctor.database.MyDoctorDatabase
import com.example.mydoctor.databinding.ActivitySignInBinding
import com.example.mydoctor.home.HomeActivity
import com.example.mydoctor.ui.signup.SignUpActivity
import com.google.android.material.snackbar.Snackbar

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

//        signUp()
        bindViewModel()
        bindView()
    }

    private fun bindViewModel() {
        viewModel.shouldShowError.observe(this){
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
        binding.inputEmail.doAfterTextChanged {
            viewModel.onChangeEmail(it.toString())
        }

        binding.inputPassword.doAfterTextChanged {
            viewModel.onChangePassword(it.toString())
        }

        binding.btnSignIn.setOnClickListener {
            viewModel.onClickSignIn()
        }
    }

    fun signUp() {
        val email = binding.inputEmail.text
        val password = binding.inputPassword.text

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)

            intent.putExtra(SignUpActivity.INTENT_EMAIL, "$email")
            intent.putExtra(SignUpActivity.INTENT_PASSWORD, "$password")
            startActivity(intent)
        }
    }

    private fun emailValidated(target: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    // contoh penggunaan toast
    fun signIn(pref: SharedPreferences) {

        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(
                binding.btnSignIn.context,
                "Email dan Password tidak boleh kosong",
                Toast.LENGTH_SHORT
            )
                .show()
        } else if (email.isEmpty()) {
            Toast.makeText(
                binding.btnSignIn.context,
                "Email tidak boleh kosong",
                Toast.LENGTH_SHORT
            )
                .show()
        } else if (!emailValidated(email.trim())) {
            Toast.makeText(binding.btnSignIn.context, "Email tidak valid", Toast.LENGTH_SHORT)
                .show()
        } else if (password.isEmpty()) {
            Toast.makeText(
                binding.btnSignIn.context,
                "Password tidak boleh kosong",
                Toast.LENGTH_SHORT
            )
                .show()
        } else if (password.length < 8) {
            Toast.makeText(
                binding.btnSignIn.context,
                "Password kurang dari 8 karakter",
                Toast.LENGTH_SHORT
            )
                .show()
        } else if (!password.matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
            Toast.makeText(
                binding.btnSignIn.context,
                "Password harus mengandung upper case dan lowercase",
                Toast.LENGTH_SHORT
            )
                .show()
        } else {
            val prefEmail = pref.getString(Constant.Preferences.KEY.EMAIL, "")
            val prefPassword = pref.getString(Constant.Preferences.KEY.PASSWORD, "")
            if (email == prefEmail && password == prefPassword) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    binding.btnSignIn.context,
                    "Email dan Password Salah",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        }

    }

    // contoh penggunaan dialog
//    fun signIn(pref: SharedPreferences) {
//
//        val email = binding.inputEmail.text.toString()
//        val password = binding.inputPassword.text.toString()
//        if (email.isEmpty() || password.isEmpty()) {
//            val dialog = AlertDialog.Builder(this)
//            dialog.setTitle("WARNING!!!")
//            dialog.setMessage("Email dan Password tidak boleh kosong")
//            dialog.show()
//        } else if (email.isEmpty()) {
//            val dialogAction = AlertDialog.Builder(this)
//            dialogAction.setTitle("WARNING!!!")
//            dialogAction.setMessage("Email tidak boleh kosong")
//            dialogAction.setPositiveButton("Ok", object : DialogInterface.OnClickListener {
//                override fun onClick(dialog: DialogInterface?, which: Int) {
//                    dialog?.dismiss()
//                }
//            })
//            dialogAction.show()
//        } else if (!emailValidated(email.trim())) {
//            Toast.makeText(binding.btnSignIn.context, "Email tidak valid", Toast.LENGTH_SHORT)
//                .show()
//        } else if (password.isEmpty()) {
//
//            val dialog = CustomDialogFragment()
//            dialog.show(supportFragmentManager, null)
//        } else if (password.length < 8) {
//            val view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null, false)
//            val dialog = AlertDialog.Builder(this)
//            dialog.setView(view)
//            dialog.create().show()
//        } else if (!password.matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
//            Toast.makeText(
//                binding.btnSignIn.context,
//                "Password harus mengandung upper case dan lowercase",
//                Toast.LENGTH_SHORT
//            )
//                .show()
//        } else {
//            val prefEmail = pref.getString(Constant.Preferences.KEY.EMAIL, "")
//            val prefPassword = pref.getString(Constant.Preferences.KEY.PASSWORD, "")
//            if (email == prefEmail && password == prefPassword) {
//                val intent = Intent(this, NavigationActivity::class.java)
//                startActivity(intent)
//            } else {
//                Toast.makeText(
//                    binding.btnSignIn.context,
//                    "Email dan Password Salah",
//                    Toast.LENGTH_SHORT
//                )
//                    .show()
//            }
//
//        }
//
//    }

    // contoh pengguanan sharedpreferences di dalam sign in
//    fun sharedPreferences(pref: SharedPreferences){
//        val email = binding.inputEmail.text.toString()
//        val password = binding.inputPassword.text.toString()
//
//        //untuk melakukan penyimpanan ke SharedPreferences
//        pref.edit {
//            putString(Constant.Preferences.KEY.EMAIL, email)
//            putString(Constant.Preferences.KEY.PASSWORD, password)
//            apply()
//        }
//
//        //untuk mengambil data dari SharedPreferences
//        val prefEmail = pref.getString(Constant.Preferences.KEY.EMAIL, "")
//        val prefPassword = pref.getString(Constant.Preferences.KEY.PASSWORD, "")
//        Snackbar.make(
//            binding.root,
//            "Value yang tersimpan adalah berikut: Email: $prefEmail dan Password: $prefPassword",
//            Snackbar.LENGTH_INDEFINITE
//        ).show()
//    }

    // contoh satu lagi pengguanaan shared preferences untuk konfigurasi aplikasi
//    fun sharedPrefDarkMode(pref: SharedPreferences, darkMode: Boolean) {
//        pref.edit {
//            putBoolean(Constant.Preferences.KEY.DARK_MODE, darkMode)
//        }
//        pref.getBoolean(Constant.Preferences.KEY.DARK_MODE, false)
//    }

    // contoh satu lagi pengguanaan shared preferences untuk konfigurasi aplikasi
//    fun sharedPrefLanguage(pref: SharedPreferences, language: String) {
//        pref.edit {
//            putString(Constant.Preferences.KEY.APP_LANGUAGE, language)
//        }
//        pref.getString(Constant.Preferences.KEY.APP_LANGUAGE, "id")
//    }
}