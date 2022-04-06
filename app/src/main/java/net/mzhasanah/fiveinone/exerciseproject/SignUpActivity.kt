package net.mzhasanah.fiveinone.exerciseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import net.mzhasanah.fiveinone.exerciseproject.galleryfragment.GalleryActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

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