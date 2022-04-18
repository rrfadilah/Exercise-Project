package com.rizky.exercise_project.ui.signin

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.database.MyDoctorDatabase
import com.rizky.exercise_project.ui.signup.RegisterFormActivity
import com.rizky.exercise_project.databinding.ActivityLoginFormBinding
import com.rizky.exercise_project.menu.MenuActivity

class LoginFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginFormBinding
    private val viewModel: LoginFormViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginFormBinding.inflate(layoutInflater)
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

        viewModel.shouldOpenHomePage.observe(this) {
            if (it) {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun bindView() {
        binding.formEmail.doAfterTextChanged {
            viewModel.onChangeEmail(it.toString())
        }

        binding.formPassword.doAfterTextChanged {
            viewModel.onChangePassword(it.toString())
        }

        binding.btnSignIn.setOnClickListener {
            viewModel.onClickSignIn()
        }
    }

}