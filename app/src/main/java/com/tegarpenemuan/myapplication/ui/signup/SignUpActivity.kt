package com.tegarpenemuan.myapplication.ui.signup

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.tegarpenemuan.myapplication.Constant
import com.tegarpenemuan.myapplication.database.MyDoctorDatabase
import com.tegarpenemuan.myapplication.databinding.ActivitySignupBinding
import com.tegarpenemuan.myapplication.home.ui.HomeActivity
import com.tegarpenemuan.myapplication.ui.signin.SignInActivity
import com.tegarpenemuan.myapplication.utils.showCustomToast

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    private val viewModel: SignUpViewModel by viewModels()
    private val progressDialog: ProgressDialog by lazy { ProgressDialog(this) }
    private val progressBar: ProgressBar by lazy { ProgressBar(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindView()
        bindViewModel()

        val db = MyDoctorDatabase.getInstance(this.applicationContext)
        val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)
        viewModel.onViewLoaded(db, pref)

        val registerPreferences =
            this.getSharedPreferences(Constant.Register.PREF_REGISTER_NAME, MODE_PRIVATE)

        binding.ibBack.setOnClickListener {
            onBackPressed()
        }

//        binding.btnContinue.setOnClickListener {
//            if (binding.etRegisterFullName.text.isEmpty()) {
//                Toast(this).showCustomToast("Fullname Tidak Boleh Kosong", this)
//            } else if (binding.etRegisterPekerjaan.text.isEmpty()) {
//                Toast(this).showCustomToast("Pekerjaan Tidak Boleh Kosong", this)
//            } else if (binding.etRegisterEmailAddress.text.isEmpty()) {
//                Toast(this).showCustomToast("Email Tidak Boleh Kosong", this)
//            } else if (binding.etRegisterPassword.text.isEmpty()) {
//                Toast(this).showCustomToast("Pekerjaan Tidak Boleh Kosong", this)
//            } else if (!binding.etRegisterEmailAddress.text.matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))) {
//                Toast(this).showCustomToast("Email Tidak Valid", this)
//            } else if (binding.etRegisterPassword.text.length < 8) {
//                Toast(this).showCustomToast("Password harus lebih dari 8 karakter", this)
//            } else if (!binding.etRegisterPassword.text.matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
//                Toast(this).showCustomToast("Password harus mengandung upper case dan lowercase", this)
//            } else if (registerPreferences.getString(
//                    Constant.Register.KEY.NAMA, ""
//                ) == binding.etRegisterFullName.text.toString()
//                || registerPreferences.getString(
//                    Constant.Register.KEY.PASSWORD,
//                    ""
//                ) == binding.etRegisterPassword.text.toString()
//                || registerPreferences.getString(
//                    Constant.Register.KEY.EMAIL,
//                    ""
//                ) == binding.etRegisterEmailAddress.text.toString()
//                || registerPreferences.getString(
//                    Constant.Register.KEY.PEKERJAAN,
//                    ""
//                ) == binding.etRegisterPassword.text.toString()
//            ) {
//                Toast(this).showCustomToast("Data Sudah Ada Di Sharepreferences", this)
//            } else {
//                val editor = registerPreferences.edit()
//                editor.putString(
//                    Constant.Register.KEY.EMAIL,
//                    binding.etRegisterEmailAddress.text.toString()
//                )
//                editor.putString(
//                    Constant.Register.KEY.PASSWORD,
//                    binding.etRegisterPassword.text.toString()
//                )
//                editor.putString(Constant.Register.KEY.NAMA, binding.etRegisterFullName.text.toString())
//                editor.putString(
//                    Constant.Register.KEY.PEKERJAAN,
//                    binding.etRegisterPekerjaan.text.toString()
//                )
//                editor.putBoolean(Constant.Register.KEY.LOGIN, false)
//                editor.apply()
//
//                val intent = Intent(this, SignInActivity::class.java)
//                startActivity(intent)
//            }
//        }
    }

    private fun bindView() {
        binding.etRegisterFullName.doAfterTextChanged {
            viewModel.onChangeName(it.toString())
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
            viewModel.onValidate()
        }
    }

    private fun bindViewModel() {
        viewModel.shouldShowError.observe(this) {
            val snackbar = Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
            snackbar.view.setBackgroundColor(Color.RED)
            snackbar.show()
        }

        viewModel.shouldShowLoading.observe(this) {
            if (it) {
                progressDialog.setMessage("Loading...")
                progressDialog.show()
            } else {
                progressDialog.hide()
            }
        }

        viewModel.shouldOpenUpdateProfile.observe(this) {
            if (it) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}