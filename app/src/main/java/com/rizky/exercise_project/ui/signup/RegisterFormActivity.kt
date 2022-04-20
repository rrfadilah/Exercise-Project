package com.rizky.exercise_project.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.database.MyDoctorDatabase
import com.rizky.exercise_project.databinding.ActivityRegisterFormBinding
import com.rizky.exercise_project.ui.signin.LoginFormActivity

class RegisterFormActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterFormBinding
    private val viewModel: RegisterFormViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterFormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val db = MyDoctorDatabase.getInstance(this.applicationContext)
        val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)
        viewModel.onViewLoaded(db, pref)

        bindViewModel()
        bindView()
    }

    private fun bindViewModel() {
        viewModel.shouldShowError.observe(this){
            val snackbar = Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
            snackbar.show()
        }

        viewModel.shouldOpenSignIn.observe(this) {
            if (it) {
                val intent = Intent(this, LoginFormActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun bindView() {
        binding.formFullName.doAfterTextChanged {
            viewModel.onChangeFullName(it.toString())
        }

        binding.formPekerjaan.doAfterTextChanged {
            viewModel.onChangeJob(it.toString())
        }

        binding.formEmail.doAfterTextChanged {
            viewModel.onChangeEmail(it.toString())
        }

        binding.formPassword.doAfterTextChanged {
            viewModel.onChangePassword(it.toString())
        }

        binding.btnRegisterContinue.setOnClickListener {
            viewModel.onClickRegister()
        }
    }
}