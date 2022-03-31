package com.example.exercise_project

import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        val mail = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        val edtmail = findViewById<EditText>(R.id.et_email)
        val edtpassword = findViewById<EditText>(R.id.et_password)

        edtmail.setText(mail)
        edtpassword.setText(password)

        openPage()
        val btnlogin = findViewById<TextView>(R.id.btn_sign_in)
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

        btnlogin.setOnClickListener {
            if (edtmail.text.toString().isEmpty() || edtpassword.text.toString().isEmpty()) {
                dialogEmailPasswordKosong()
                dialogActionEmailPasswordKosong()
                dialogEmailPasswordCustomLayoutKosong()
                dialogEmailPasswordFragmentKosong()
            } else if (!edtpassword.text.toString().matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+")) || !edtmail.text.toString()
                    .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$")) || edtpassword.text.toString().length < 8
            ) {
                dialogEmailPasswordValidation()
                dialogActionEmailPasswordValidation()
                dialogEmailPasswordCustomLayoutValidation()
                dialogEmailPasswordFragmentValidation()

            } else {
                openPage()
            }


        }

        val btnCreateNewAccount = findViewById<TextView>(R.id.tv_create_new_account)
        btnCreateNewAccount.setOnClickListener {
            Toast.makeText(this, "Tombol create new account di pencet", Toast.LENGTH_SHORT).show()
        }
        val btnForgotPassword = findViewById<TextView>(R.id.tv_forgot_password)
        btnForgotPassword.setOnClickListener {
            Toast.makeText(this, "Tombol forgot password di pencet", Toast.LENGTH_SHORT).show()
        }

        val dialog1 = AlertDialog.Builder(this)
        dialog1.setTitle("judul dialog")
        dialog1.setMessage("isi pesan dialog")
        dialog1.setCancelable(true)
        dialog1.show()

        val dialogAction = AlertDialog.Builder(this)
        dialogAction.setTitle("judul dialog")
        dialogAction.setMessage("konfirmasi keluar?")
        //menggunakan object DialogInterface.OnClickListener
        dialogAction.setPositiveButton("ya", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.dismiss()
            }
        })
        //menggunakan lambda
        dialogAction.setNegativeButton("tidak") { dialog, which ->
            dialog?.dismiss()
        }
        dialogAction.setNeutralButton("netral") { dialog, which ->
            dialog?.dismiss()
        }
//        dialogAction.show()
//        dialogCustomLayout()
//        dialogWithFragment()
    }

    private fun dialogCustomLayout() {
        val view = layoutInflater.inflate(R.layout.dialog_custom, null, false)
        val dialog = AlertDialog.Builder(this)
        dialog.setView(view)
        dialog.setCancelable(true)
        dialog.show()
    }

    private fun dialogWithFragment() {
        val dialog = CustomDialogFragment()
        dialog.show(supportFragmentManager, null)
    }

    private fun openPage() {

        Toast.makeText(this, "Membuka halaman Sign In", Toast.LENGTH_LONG).show()
        val snackbar = Snackbar.make(
            findViewById(R.id.cl_root),
            "Membuka halaman Sign In",
            Snackbar.LENGTH_LONG
        )
        snackbar.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Tombol back di pencet", Toast.LENGTH_SHORT).show()
    }

    private fun dialogActionEmailPasswordValidation() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("email dan password tidak valid")
        dialog.setMessage("Apakah anda yakin email dan password sudah benar?")
        dialog.setCancelable(true)
        dialog.setPositiveButton("ya") { dialog, which ->
            dialog?.dismiss()
        }
        dialog.setNegativeButton("tidak") { dialog, which ->
            dialog?.dismiss()
        }
        dialog.show()
    }

    private fun dialogEmailPasswordValidation() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("email dan password tidak valid")
        dialog.setMessage("email dan password tidak valid")
        dialog.setCancelable(true)
        dialog.show()
    }

    private fun dialogEmailPasswordFragmentValidation() {
        val dialog = EmailDanPasswordValidation()
        dialog.show(supportFragmentManager, null)
    }

    private fun dialogEmailPasswordCustomLayoutValidation() {
        val view = layoutInflater.inflate(R.layout.dialog_email_password, null, false)
        val dialog = AlertDialog.Builder(this)
        dialog.setView(view)
        dialog.setCancelable(true)
        dialog.show()
    }

    private fun dialogEmailPasswordKosong() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("email dan password kosong")
        dialog.setMessage("email dan password kosong")
        dialog.setCancelable(true)
        dialog.show()
    }

    private fun dialogActionEmailPasswordKosong() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("email dan password kosong")
        dialog.setMessage("email dan password kosong, isi terlebih dahulu")
        dialog.setCancelable(true)
        dialog.setPositiveButton("oke") { dialog, which ->
            dialog?.dismiss()
        }
        dialog.setNegativeButton("tidak") { dialog, which ->
            dialog?.dismiss()
        }
        dialog.show()
    }

    fun dialogEmailPasswordFragmentKosong() {
        val dialog = DialogEmailDanPasswordKosong()
        dialog.show(supportFragmentManager, null)
    }

    fun dialogEmailPasswordCustomLayoutKosong() {
        val view = layoutInflater.inflate(R.layout.dialog_email_password_kosong, null, false)
        val dialog = AlertDialog.Builder(this)
        dialog.setView(view)
        dialog.setCancelable(true)
        dialog.show()
    }
}