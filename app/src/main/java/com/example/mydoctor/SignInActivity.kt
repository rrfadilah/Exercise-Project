package com.example.mydoctor

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mydoctor.databinding.ActivitySignInBinding
import java.util.regex.Pattern
import java.util.regex.Pattern.compile

class SignInActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sign_in)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signIn()
        signUp()
    }

    private fun emailValidated(target: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun signIn() {

        binding.btnSignIn.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()
//            val emailRegex = compile(
//                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
//                        "\\@" +
//                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
//                        "(" +
//                        "\\." +
//                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
//                        ")+"
//            )
//
//            val passwordRegex = compile(
//                "^(?=.*[A-Z])(?=.*[a-z])$"
//            )

            if(email.isEmpty() && password.isEmpty()){
//                Toast.makeText(binding.btnSignIn.context, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT)
//                    .show()
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("WARNING!!!")
                dialog.setMessage("Email dan Password tidak boleh kosong")
                dialog.show()

            }else if (email.isEmpty()) {
//                Toast.makeText(
//                    binding.btnSignIn.context,
//                    "Email tidak boleh kosong",
//                    Toast.LENGTH_SHORT
//                )
//                    .show()

                val dialogAction = AlertDialog.Builder(this)
                dialogAction.setTitle("WARNING!!!")
                dialogAction.setMessage("Email tidak boleh kosong")
                dialogAction.setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                    }
                })
                dialogAction.show()
            } else if (!emailValidated(email.trim())) {
                Toast.makeText(binding.btnSignIn.context, "Email tidak valid", Toast.LENGTH_SHORT)
                    .show()
            } else if (password.isEmpty()) {

                val dialog = CustomDialogFragment()
                dialog.show(supportFragmentManager, null)

//                Toast.makeText(
//                    binding.btnSignIn.context,
//                    "Password tidak boleh kosong",
//                    Toast.LENGTH_SHORT
//                )
//                    .show()
            } else if (password.length<8) {
                val view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null, false)
                val dialog = AlertDialog.Builder(this)
                dialog.setView(view)
                dialog.create().show()

//                Toast.makeText(
//                    binding.btnSignIn.context,
//                    "Password kurang dari 8 karakter",
//                    Toast.LENGTH_SHORT
//                )
//                    .show()



                ///////////////////////////
            }else if (!password.matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
                Toast.makeText(
                    binding.btnSignIn.context,
                    "Password harus mengandung upper case dan lowercase",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                val intent = Intent(this, NavigationActivity::class.java)
                startActivity(intent)
            }

        }


///////////////////////////////////////////
//        binding.btnSignIn.setOnClickListener {
//            val intent = Intent(this, NavigationActivity::class.java)
//            startActivity(intent)
//        }
    }

    fun signUp() {
        val email = binding.inputEmail.text
        val password = binding.inputPassword.text

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)

            intent.putExtra(SignUpActivity.INTENT_EMAIL, "$email")
            intent.putExtra(SignUpActivity.INTENT_PASSWORD, "$password")
            startActivity(intent)
        }
    }

}