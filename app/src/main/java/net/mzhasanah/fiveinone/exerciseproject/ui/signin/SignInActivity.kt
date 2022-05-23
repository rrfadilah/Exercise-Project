package net.mzhasanah.fiveinone.exerciseproject.ui.signin

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.activity.viewModels
import net.mzhasanah.fiveinone.exerciseproject.Constant
import net.mzhasanah.fiveinone.exerciseproject.R
import net.mzhasanah.fiveinone.exerciseproject.customdialog.*
import net.mzhasanah.fiveinone.exerciseproject.database.MyDoctorDatabase
import net.mzhasanah.fiveinone.exerciseproject.databinding.ActivitySignInBinding
import net.mzhasanah.fiveinone.exerciseproject.ui.signup.SignUpActivity
import net.mzhasanah.fiveinone.exerciseproject.ui.home.HomeActivity

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

        val editEmail = findViewById<EditText>(R.id.etEmail)
        val editPassword = findViewById<EditText>(R.id.etPassword)
        val registerPreferences =
            this.getSharedPreferences(Constant.Register.PREF_REGISTER_NAME, MODE_PRIVATE)
        val btnlogin = findViewById<TextView>(R.id.btnSignIn)

        btnlogin.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            if (email.isEmpty() && password.isEmpty()) {
                Toast.makeText(this, R.string.EmailPasswordKosong, Toast.LENGTH_SHORT).show()
            } else if (email.isEmpty()) {
                Toast.makeText(this, R.string.EmailKosong, Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, R.string.PasswordKosong, Toast.LENGTH_SHORT).show()
            } else if (password.length < 8) {
                Toast.makeText(this, R.string.PasswordKurang, Toast.LENGTH_SHORT).show()
            } else if (!password.matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
                Toast.makeText(this, R.string.PasswordUpLow, Toast.LENGTH_SHORT).show()
            } else if (!email
                    .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))
            ) {
                Toast.makeText(this, R.string.EmailInvalid, Toast.LENGTH_SHORT).show()
            } else {
                val emailPreferences =
                    registerPreferences.getString(Constant.Register.KEY.EMAIL, "")
                val passwordPreferences =
                    registerPreferences.getString(Constant.Register.KEY.PASSWORD, "")
                if (email == emailPreferences && password == passwordPreferences) {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Snackbar.make(
                        it, "Email atau password tidak terdaftar",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
        val btnRegister = findViewById<TextView>(R.id.tvCreateAccount)
        btnRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val forgot = findViewById<TextView>(R.id.tvForgotPassword)
        forgot.setOnClickListener {
            Toast.makeText(this, R.string.ForgotPassword, Toast.LENGTH_SHORT).show()
        }

        val createNewAccount = findViewById<TextView>(R.id.tvCreateAccount)
        createNewAccount.setOnClickListener {
            val etDataEmail = findViewById<View>(R.id.etEmail) as EditText
            val etDataPassword = findViewById<View>(R.id.etPassword) as EditText
            val intent = Intent(this, SignUpActivity::class.java)
            intent.putExtra("dataEmail", etDataEmail.getText().toString())
            intent.putExtra("dataPassword", etDataPassword.getText().toString())
            startActivity(intent)
        }

//        openPage()
//        binding = ActivitySignInBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        dialogStandard()
//        dialogAction()
//        dialogCustomLayout()
//        dialogWithFragment()

//        val login = findViewById<Button>(R.id.btnSignIn)
//        login.setOnClickListener{
//            val e_mail = binding.etEmail.text.toString()
//            val password = binding.etPassword.text.toString()
//
//            val pref = this.getSharedPreferences(Constant.Preferences.PREF_NAME, MODE_PRIVATE)
//            pref.edit {
//                putString(Constant.Preferences.KEY.EMAIL, e_mail.toString())
//                putString(Constant.Preferences.KEY.PASSWORD, password.toString())
//                apply()
//            }
//
//            val prefEmail = pref.getString(Constant.Preferences.KEY.EMAIL, "")
//            val prefPassword = pref.getString(Constant.Preferences.KEY.PASSWORD, "")
//            Snackbar.make(
//                binding.root,
//                "Value yang tersimpan adalah berikut: Email: $prefEmail Password: $prefPassword",
//                Snackbar.LENGTH_LONG
//            ).show()
//        }
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
        Toast.makeText(this, "Membuka halaman Sign In", Toast.LENGTH_LONG).show()
    }

    fun dialogStandard(){
        val e_mail = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val isiEmail = findViewById<EditText>(R.id.etEmail)
        val isiPassword = findViewById<EditText>(R.id.etPassword)

        isiEmail.setText(e_mail)
        isiPassword.setText(password)

        val btnlogin = findViewById<TextView>(R.id.btnSignIn)
        btnlogin.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            if(isiEmail.text.toString().isEmpty() && isiPassword.text.toString().isEmpty()){
                dialog.setTitle("Email dan password kosong")
                dialog.setMessage("Silakan isi email dan password anda")
                dialog.show()
            }
            else if (!isiEmail.text.toString()
                    .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$")) && !isiEmail.text.toString()
                    .isEmpty()
            ) {
//           Toast.makeText(this, R.string.EmailInvalid, Toast.LENGTH_SHORT).show()
                dialog.setTitle("Email tidak valid")
                dialog.setMessage("Silakan isi email anda dengan benar")
                dialog.show()
            } else if (isiEmail.text.toString().isEmpty()) {
//                Toast.makeText(this, R.string.EmailKosong, Toast.LENGTH_SHORT).show()
                dialog.setTitle("Email tidak boleh kosong")
                dialog.setMessage("Silakan isi email anda")
                dialog.show()
            } else if (isiPassword.text.toString().isEmpty()) {
//                Toast.makeText(this, R.string.PasswordKosong, Toast.LENGTH_SHORT).show()
                dialog.setTitle("Password tidak boleh kosong")
                dialog.setMessage("Silakan isi password anda")
                dialog.show()
            } else if (isiPassword.text.toString().length < 8) {
//                Toast.makeText(this, R.string.PasswordKurang, Toast.LENGTH_SHORT).show()
                dialog.setTitle("Password lemah")
                dialog.setMessage("Silakan isi password anda minimal 8 karakter")
                dialog.show()
            } else if (!isiPassword.text.toString().matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
//                Toast.makeText(this, R.string.PasswordUpLow, Toast.LENGTH_SHORT).show()
                dialog.setTitle("Password lemah")
                dialog.setMessage("Silakan isi password anda dengan\nmengandung uppercase dan lowercase")
                dialog.show()
            }
        }
    }

    fun dialogAction(){
        val e_mail = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val isiEmail = findViewById<EditText>(R.id.etEmail)
        val isiPassword = findViewById<EditText>(R.id.etPassword)

        isiEmail.setText(e_mail)
        isiPassword.setText(password)

        val btnlogin = findViewById<TextView>(R.id.btnSignIn)
        btnlogin.setOnClickListener {
            val dialogAction = AlertDialog.Builder(this)
            if(isiEmail.text.toString().isEmpty() && isiPassword.text.toString().isEmpty()){
                dialogAction.setTitle("Email dan Password kosong")
                dialogAction.setMessage("Silakan isi email dan password anda")
                dialogAction.setPositiveButton("Siap", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                    }
                })
                dialogAction.setNegativeButton("Tutup") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.setNeutralButton("Bantuan") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.show()
            } else if (!isiEmail.text.toString()
                    .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$")) && !isiEmail.text.toString()
                    .isEmpty()
            ) {
                dialogAction.setTitle("Email tidak valid")
                dialogAction.setMessage("Silakan isi email anda dengan benar")
                dialogAction.setPositiveButton("Siap", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                    }
                })
                dialogAction.setNegativeButton("Tutup") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.setNeutralButton("Bantuan") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.show()
            } else if (isiEmail.text.toString().isEmpty()) {
                dialogAction.setTitle("Email tidak boleh kosong")
                dialogAction.setMessage("Silakan isi email anda")
                dialogAction.setPositiveButton("Siap", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                    }
                })
                dialogAction.setNegativeButton("Tutup") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.setNeutralButton("Bantuan") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.show()
            } else if (isiPassword.text.toString().isEmpty()) {
                dialogAction.setTitle("Password tidak boleh kosong")
                dialogAction.setMessage("Silakan isi password anda")
                dialogAction.setPositiveButton("Siap", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                    }
                })
                dialogAction.setNegativeButton("Tutup") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.setNeutralButton("Bantuan") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.show()
            } else if (isiPassword.text.toString().length < 8) {
                dialogAction.setTitle("Password lemah")
                dialogAction.setMessage("Silakan isi password anda minimal 8 karakter")
                dialogAction.setPositiveButton("Siap", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                    }
                })
                dialogAction.setNegativeButton("Tutup") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.setNeutralButton("Bantuan") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.show()
            } else if (!isiPassword.text.toString().matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
                dialogAction.setTitle("Password lemah")
                dialogAction.setMessage("Silakan isi password anda dengan\nmengandung uppercase dan lowercase")
                dialogAction.setPositiveButton("Siap", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                    }
                })
                dialogAction.setNegativeButton("Tutup") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.setNeutralButton("Bantuan") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.show()
            }
        }
    }

    fun dialogCustomLayout(){
        val e_mail = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val isiEmail = findViewById<EditText>(R.id.etEmail)
        val isiPassword = findViewById<EditText>(R.id.etPassword)

        isiEmail.setText(e_mail)
        isiPassword.setText(password)

        val btnlogin = findViewById<TextView>(R.id.btnSignIn)
        btnlogin.setOnClickListener {
            if (isiEmail.text.toString().isEmpty() && isiPassword.text.toString().isEmpty()) {
                val view = LayoutInflater.from(this).inflate(R.layout.dialog_custom_emailpassword_kosong, null, false)
                val dialog = AlertDialog.Builder(this)
                dialog.setView(view)
                dialog.create().show()
            } else if (!isiEmail.text.toString()
                    .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$")) && !isiEmail.text.toString()
                    .isEmpty()
            ) {
                val view = LayoutInflater.from(this).inflate(R.layout.dialog_custom_email_invalid, null, false)
                val dialog = AlertDialog.Builder(this)
                dialog.setView(view)
                dialog.create().show()
            } else if (isiEmail.text.toString().isEmpty()) {
                val view = LayoutInflater.from(this).inflate(R.layout.dialog_custom_email_kosong, null, false)
                val dialog = AlertDialog.Builder(this)
                dialog.setView(view)
                dialog.create().show()
            } else if (isiPassword.text.toString().isEmpty()) {
                val view = LayoutInflater.from(this).inflate(R.layout.dialog_custom_password_kosong, null, false)
                val dialog = AlertDialog.Builder(this)
                dialog.setView(view)
                dialog.create().show()
            } else if (isiPassword.text.toString().length < 8) {
                val view = LayoutInflater.from(this).inflate(R.layout.dialog_custom_password_kurang, null, false)
                val dialog = AlertDialog.Builder(this)
                dialog.setView(view)
                dialog.create().show()
            } else if (!isiPassword.text.toString().matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
                val view = LayoutInflater.from(this).inflate(R.layout.dialog_custom_password_uplow, null, false)
                val dialog = AlertDialog.Builder(this)
                dialog.setView(view)
                dialog.create().show()
            }
        }
    }

    fun dialogWithFragment(){
        val e_mail = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val isiEmail = findViewById<EditText>(R.id.etEmail)
        val isiPassword = findViewById<EditText>(R.id.etPassword)

        isiEmail.setText(e_mail)
        isiPassword.setText(password)

        val btnlogin = findViewById<TextView>(R.id.btnSignIn)
        btnlogin.setOnClickListener {
            if(isiEmail.text.toString().isEmpty() && isiPassword.text.toString().isEmpty()){
                val dialog = CostumDialogEmailPasswordKosongFragment()
                dialog.show(supportFragmentManager, null)
            }
            else if (!isiEmail.text.toString()
                    .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$")) && !isiEmail.text.toString()
                    .isEmpty()
            ) {
                val dialog = CostumDialogEmailInvalidFragment()
                dialog.show(supportFragmentManager, null)
            } else if (isiEmail.text.toString().isEmpty()) {
                val dialog = CostumDialogEmailKosongFragment()
                dialog.show(supportFragmentManager, null)
            } else if (isiPassword.text.toString().isEmpty()) {
                val dialog = CostumDialogPasswordKosongFragment()
                dialog.show(supportFragmentManager, null)
            } else if (isiPassword.text.toString().length < 8) {
                val dialog = CostumDialogPasswordKurangFragment()
                dialog.show(supportFragmentManager, null)
            } else if (!isiPassword.text.toString().matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
                val dialog = CostumDialogPasswordUpLowFragment()
                dialog.show(supportFragmentManager, null)
            }
        }
    }
}
