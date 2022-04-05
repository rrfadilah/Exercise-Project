package com.example.exercise_project

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.exercise_project.home.HomeActivity
import com.google.android.material.snackbar.Snackbar

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

//        val mail = intent.getStringExtra("email")
////        val password = intent.getStringExtra("password")
//        edtmail.setText(mail)
//        edtpassword.setText(password)

        //get text email and password
        val edtmail = findViewById<EditText>(R.id.et_email)
        val edtpassword = findViewById<EditText>(R.id.et_password)
        val registerPreferences =
            this.getSharedPreferences(Constant.Register.PREF_REGISTER_NAME, MODE_PRIVATE)
        val btnlogin = findViewById<TextView>(R.id.btn_sign_in)

        btnlogin.setOnClickListener {
            val email = edtmail.text.toString()
            val password = edtpassword.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            } else if (email.isEmpty()) {
                Toast.makeText(this, R.string.email_kosong, Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, R.string.password_kosong, Toast.LENGTH_SHORT).show()
            } else if (password.length < 8) {
                Toast.makeText(this, R.string.password_character, Toast.LENGTH_SHORT).show()
            } else if (!password.matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
                Toast.makeText(this, R.string.password_lower_upper_case, Toast.LENGTH_SHORT).show()
            } else if (!email
                    .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))
            ) {
                Toast.makeText(this, R.string.email_salah, Toast.LENGTH_SHORT).show()
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
                        it, "Email or password is incorrect",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
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