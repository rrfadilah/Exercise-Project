package com.example.mydoctor

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import com.example.mydoctor.databinding.ActivitySignInBinding
import com.google.android.material.snackbar.Snackbar

class SignInActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sign_in)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)
        binding.btnSignIn.setOnClickListener {
            sharedPreferences(pref)
        }
//        signIn()
        signUp()
    }

    private fun emailValidated(target: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun signIn() {

        binding.btnSignIn.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()
//            val emailRegex = compile(
//                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
//                        "\\@" +
//                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
//                        "(" +
//                        "\\." +
//                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
//                        ")+"
//            )
//
//            val passwordRegex = compile(
//                "^(?=.*[A-Z])(?=.*[a-z])$"
//            )

            if (email.isEmpty() && password.isEmpty()) {
//                Toast.makeText(binding.btnSignIn.context, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT)
//                    .show()
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("WARNING!!!")
                dialog.setMessage("Email dan Password tidak boleh kosong")
                dialog.show()

            } else if (email.isEmpty()) {
//                Toast.makeText(
//                    binding.btnSignIn.context,
//                    "Email tidak boleh kosong",
//                    Toast.LENGTH_SHORT
//                )
//                    .show()

                val dialogAction = AlertDialog.Builder(this)
                dialogAction.setTitle("WARNING!!!")
                dialogAction.setMessage("Email tidak boleh kosong")
                dialogAction.setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                    }
                })
                dialogAction.show()
            } else if (!emailValidated(email.trim())) {
                Toast.makeText(binding.btnSignIn.context, "Email tidak valid", Toast.LENGTH_SHORT)
                    .show()
            } else if (password.isEmpty()) {

                val dialog = CustomDialogFragment()
                dialog.show(supportFragmentManager, null)

//                Toast.makeText(
//                    binding.btnSignIn.context,
//                    "Password tidak boleh kosong",
//                    Toast.LENGTH_SHORT
//                )
//                    .show()
            } else if (password.length < 8) {
                val view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null, false)
                val dialog = AlertDialog.Builder(this)
                dialog.setView(view)
                dialog.create().show()

//                Toast.makeText(
//                    binding.btnSignIn.context,
//                    "Password kurang dari 8 karakter",
//                    Toast.LENGTH_SHORT
//                )
//                    .show()


                ///////////////////////////
            } else if (!password.matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
                Toast.makeText(
                    binding.btnSignIn.context,
                    "Password harus mengandung upper case dan lowercase",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                val intent = Intent(this, NavigationActivity::class.java)
                startActivity(intent)
            }

        }


///////////////////////////////////////////
//        binding.btnSignIn.setOnClickListener {
//            val intent = Intent(this, NavigationActivity::class.java)
//            startActivity(intent)
//        }
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

    // contoh pengguanan sharedpreferences di dalam sign in
    fun sharedPreferences(pref: SharedPreferences){
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()

        //untuk melakukan penyimpanan ke SharedPreferences
        pref.edit {
            putString(Constant.Preferences.KEY.EMAIL, email)
            putString(Constant.Preferences.KEY.PASSWORD, password)
            apply()
        }

        //untuk mengambil data dari SharedPreferences
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