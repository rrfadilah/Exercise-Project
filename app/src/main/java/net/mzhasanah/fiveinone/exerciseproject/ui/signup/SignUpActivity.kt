package net.mzhasanah.fiveinone.exerciseproject.ui.signup

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import net.mzhasanah.fiveinone.exerciseproject.Constant
import net.mzhasanah.fiveinone.exerciseproject.R
import net.mzhasanah.fiveinone.exerciseproject.database.MyDoctorDatabase
import net.mzhasanah.fiveinone.exerciseproject.databinding.ActivitySignUpBinding
import net.mzhasanah.fiveinone.exerciseproject.ui.galleryfragment.GalleryActivity
import net.mzhasanah.fiveinone.exerciseproject.ui.home.HomeActivity
import net.mzhasanah.fiveinone.exerciseproject.ui.onboarding.OnBoardingActivity
import net.mzhasanah.fiveinone.exerciseproject.ui.signin.SignInActivity

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

        val etEmailData = findViewById<View>(R.id.etEmailAddress) as EditText
        val etPasswordData = findViewById<View>(R.id.etPassword2) as EditText
        etEmailData.setText(intent.getStringExtra("dataEmail"))
        etPasswordData.setText(intent.getStringExtra("dataPassword"))

        val btnContinue = findViewById<Button>(R.id.btnContinue)
        val registerPref =
            this.getSharedPreferences(Constant.Register.PREF_REGISTER_NAME, MODE_PRIVATE)

        btnContinue.setOnClickListener {
            val email = findViewById<EditText>(R.id.etEmailAddress).text.toString()
            val password = findViewById<EditText>(R.id.etPassword2).text.toString()
            val nama = findViewById<EditText>(R.id.etFullName).text.toString()
            val pekerjaan = findViewById<EditText>(R.id.etPekerjaan).text.toString()


            if (email.isEmpty() || password.isEmpty() || nama.isEmpty() || pekerjaan.isEmpty()) {
                Toast.makeText(this, "Tidak boleh ada isian yang kosong", Toast.LENGTH_SHORT).show()
            } else if (!password.matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
                Toast.makeText(
                    this,
                    R.string.PasswordUpLow,
                    Toast.LENGTH_SHORT
                ).show()
            } else if (password.length < 8) {
                Toast.makeText(this, R.string.PasswordKurang, Toast.LENGTH_SHORT).show()
            } else if (!email
                    .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))
            ) {
                Toast.makeText(this, R.string.EmailInvalid, Toast.LENGTH_SHORT).show()
            } else if (registerPref.getString(Constant.Register.KEY.NAMA, "") == email
            ) {
                Toast.makeText(this, "Akun sudah terdaftar", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val editor = registerPref.edit()
                editor.putString(Constant.Register.KEY.EMAIL, email)
                editor.putString(Constant.Register.KEY.PASSWORD, password)
                editor.putString(Constant.Register.KEY.NAMA, nama)
                editor.putString(Constant.Register.KEY.PEKERJAAN, pekerjaan)
                editor.apply()

                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun bindView() {
        binding.etFullName.doAfterTextChanged {
            viewModel.onChangeName(it.toString())
        }
        binding.etPekerjaan.doAfterTextChanged {
            viewModel.onChangeJob(it.toString())
        }
        binding.etEmailAddress.doAfterTextChanged {
            viewModel.onChangeEmail(it.toString())
        }
        binding.etPassword2.doAfterTextChanged {
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

    fun ClickArrowBack(V: View?) {
        val intent = Intent(this, OnBoardingActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun ClickContinue(V: View?) {
        val intent = Intent(this, GalleryActivity::class.java)
        startActivity(intent)
    }
}