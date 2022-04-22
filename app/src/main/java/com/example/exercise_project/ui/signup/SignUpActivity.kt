package com.example.exercise_project.ui.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.exercise_project.Constant
import com.example.exercise_project.databinding.ActivitySignUpBinding
import com.example.exercise_project.home.database.MyDoctorDatabase
import com.example.exercise_project.ui.signin.SignActivity
import com.google.android.material.snackbar.Snackbar

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val db = MyDoctorDatabase.getInstance(this.applicationContext)
        val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)
        viewModel.onViewLoaded(db, pref)

        bindViewModel()
        bindView()
    }

    private fun bindViewModel() {
        viewModel.shouldShowError.observe(this) {
            val snackbar = Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
            snackbar.show()
        }

        viewModel.shouldOpenSignIn.observe(this) {
            if (it) {
                val intent = Intent(this, SignActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun bindView() {
        binding.etRegisterFullName.doAfterTextChanged {
            viewModel.onChangeFullName(it.toString())
        }

        binding.etRegisterPekerjaan.doAfterTextChanged {
            viewModel.onChangeJob(it.toString())
        }

        binding.etRegisterEmailAddress.doAfterTextChanged {
            viewModel.onChangeEmail(it.toString())
        }

        binding.etRegisterPassword.doAfterTextChanged {
            viewModel.onChangePassword(it.toString())
        }

        binding.btnContinue.setOnClickListener {
            viewModel.onClickRegister()
        }
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sign_up)
//
//        val btnContinue = findViewById<Button>(R.id.btn_continue)
//        val registerPreferences =
//            this.getSharedPreferences(Constant.Register.PREF_REGISTER_NAME, MODE_PRIVATE)
//
//
//        btnContinue.setOnClickListener {
//            val email = findViewById<EditText>(R.id.et_register_email_address).text.toString()
//            val password = findViewById<EditText>(R.id.et_register_password).text.toString()
//            val nama = findViewById<EditText>(R.id.et_register_full_name).text.toString()
//            val pekerjaan = findViewById<EditText>(R.id.et_register_pekerjaan).text.toString()
//
//
//            if (email.isEmpty() || password.isEmpty() || nama.isEmpty() || pekerjaan.isEmpty()) {
//                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
//            } else if (!password.matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
//                Toast.makeText(this, R.string.password_lower_upper_case, Toast.LENGTH_SHORT).show()
//            } else if (password.length < 8) {
//                Toast.makeText(this, R.string.password_character, Toast.LENGTH_SHORT).show()
//            } else if (!email
//                    .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))
//            ) {
//                Toast.makeText(this, R.string.email_salah, Toast.LENGTH_SHORT).show()
//            } else if (registerPreferences.getString(Constant.Register.KEY.NAMA, "") == email
//                || registerPreferences.getString(Constant.Register.KEY.PASSWORD, "") == password
//                || registerPreferences.getString(Constant.Register.KEY.EMAIL, "") == nama
//                || registerPreferences.getString(Constant.Register.KEY.PEKERJAAN, "") == pekerjaan
//            ) {
//                Toast.makeText(this, "Data sudah ada di sharedpreferences", Toast.LENGTH_SHORT)
//                    .show()
//            } else {
//                val editor = registerPreferences.edit()
//                editor.putString(Constant.Register.KEY.EMAIL, email)
//                editor.putString(Constant.Register.KEY.PASSWORD, password)
//                editor.putString(Constant.Register.KEY.NAMA, nama)
//                editor.putString(Constant.Register.KEY.PEKERJAAN, pekerjaan)
//                editor.apply()
//
//                val intent = Intent(this, SignActivity::class.java)
//                startActivity(intent)
//            }
//
//        }
//    }
}