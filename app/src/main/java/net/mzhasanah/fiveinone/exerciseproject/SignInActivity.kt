package net.mzhasanah.fiveinone.exerciseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        openPage()
        val e_mail = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        val isiEmail = findViewById<EditText>(R.id.etEmail)
        val isiPassword = findViewById<EditText>(R.id.etPassword)

        isiEmail.setText(e_mail)
        isiPassword.setText(password)

        val btnlogin = findViewById<TextView>(R.id.btnSignIn)
        btnlogin.setOnClickListener {
            if (!isiEmail.text.toString().matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$")) && !isiEmail.text.toString().isEmpty())
            {
                Toast.makeText(this, R.string.EmailInvalid, Toast.LENGTH_SHORT).show()
            } else if (isiEmail.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.EmailKosong, Toast.LENGTH_SHORT).show()
            } else if (isiPassword.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.PasswordKosong, Toast.LENGTH_SHORT).show()
            } else if (isiPassword.text.toString().length < 8) {
                Toast.makeText(this, R.string.PasswordKurang, Toast.LENGTH_SHORT).show()
            } else if (!isiPassword.text.toString().matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
                Toast.makeText(this, R.string.PasswordUpLow, Toast.LENGTH_SHORT).show()
            }
        }
        val forgot = findViewById<TextView>(R.id.tvForgotPassword)
        forgot.setOnClickListener {
            Toast.makeText(this, R.string.ForgotPassword, Toast.LENGTH_SHORT).show()
        }
    }

    fun ClickCreateNewAccount(V: View?) {
        val etDataEmail = findViewById<View>(R.id.etEmail) as EditText
        val etDataPassword = findViewById<View>(R.id.etPassword) as EditText
        var tvCreate = findViewById<View>(R.id.tvCreateAccount) as TextView
        val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
        intent.putExtra("dataEmail", etDataEmail.getText().toString())
        intent.putExtra("dataPassword", etDataPassword.getText().toString())
        startActivity(intent)
    }

    fun openPage() {
        Toast.makeText(this, "Membuka halaman Sign In", Toast.LENGTH_LONG).show()
    }
}