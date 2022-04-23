package com.rizky.exercise_project.ui.signup

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.R
import com.rizky.exercise_project.database.MyDoctorDatabase
import com.rizky.exercise_project.databinding.ActivitySignInBinding
import com.rizky.exercise_project.databinding.ActivitySignUpBinding
import com.rizky.exercise_project.home.HomeActivity

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModels()
    private val progressDialog: ProgressDialog by lazy { ProgressDialog(this) }
    private val progressBar: ProgressBar by lazy { ProgressBar(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindView()
        bindViewModel()

        val db = MyDoctorDatabase.getInstance(this.applicationContext)
        val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)
        viewModel.onViewLoaded(db, pref)
    }

    private fun bindView() {
        binding.etFullName.doAfterTextChanged {
            viewModel.onChangeName(it.toString())
        }
        binding.etJob.doAfterTextChanged {
            viewModel.onChangeJob(it.toString())
        }
        binding.etEmail.doAfterTextChanged {
            viewModel.onChangeEmail(it.toString())
        }
        binding.etPassword.doAfterTextChanged {
            viewModel.onChangePassword(it.toString())
        }
        binding.btnSignUp.setOnClickListener {
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