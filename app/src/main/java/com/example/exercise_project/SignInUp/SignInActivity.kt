package com.example.exercise_project.SignInUp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.exercise_project.R
import com.example.exercise_project.databinding.ActivitySignInBinding
import com.example.exercise_project.databinding.FragmentCustomDialogBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //toast&snackbar dengan kondisi
        binding.tvGoToSignUp.setOnClickListener {
            emailValidation()
            passValidation()
        }
    }

    private fun emailValidation(): Boolean {
        val email = binding.etEmailSignIn.text.toString()

        if (email.isEmpty()) {
            val dialogEmail = AlertDialog.Builder(this)
            dialogEmail.setTitle("email empty")
            dialogEmail.setMessage("String")
            dialogEmail.show()
            return false
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            val dialogValid = AlertDialog.Builder(this)
            dialogValid.setTitle("email not valid")
            dialogValid.setMessage("String")
            dialogValid.show()
            return false
        }else{
            return true
        }
    }

    private fun passValidation(): Boolean {
        val pass = binding.etPassSignIn.text.toString()

        if (pass.isEmpty()) {
            val dialogEmail = AlertDialog.Builder(this)
            dialogEmail.setTitle("pass empty")
            dialogEmail.setMessage("String")
            dialogEmail.show()
            return false
        }else if (pass.length < 8){
            val dialogValid = AlertDialog.Builder(this)
            dialogValid.setTitle("min character")
            dialogValid.setMessage("String")
            dialogValid.show()
            return false
        }else if (!pass.matches(".[a-zA-Z].".toRegex())){
            val dialogValid = AlertDialog.Builder(this)
            dialogValid.setTitle("uppercase&lowercase")
            dialogValid.setMessage("String")
            dialogValid.show()
            return false
        }else{
            return true
        }
    }

    private fun openSignUpPage(){
        Toast.makeText(this, "Anda membuka halaman sign up", Toast.LENGTH_LONG).show()
    }

    private fun openSigninPage(){
        Toast.makeText(this, "Selamat datang di halaman sign in", Toast.LENGTH_LONG).show()
    }

    private fun dialogStandard(){
        //pembuatan alertdialog menggunakan string
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Ini Judul")
        //dialog.setTitle(R.string.app_name)

        dialog.setMessage("Ini isi Pesan")
        //dialog.setMessage(R.string.app_name)

        //dialog true = bisa diclose, false = tidak bisa di close
        dialog.setCancelable(true)
        dialog.show()
    }

    //dialog dengan action
    private fun dialogAction(){
        val dialogAction = AlertDialog.Builder(this)
        dialogAction.setTitle("Ini Judul dengan Action")
        dialogAction.setMessage("Ini Isi Pesan dengan Action")
        dialogAction.setPositiveButton("Positif", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int){
                dialog?.dismiss()
            }
        })
        //set button dengan lambda expression
        dialogAction.setNegativeButton("Negative"){ dialog, which ->
            dialog.dismiss()
        }
        dialogAction.setNeutralButton("Neutral"){ dialog, which ->
            dialog.dismiss()
        }
        dialogAction.show()
    }

    private fun dialogCustomLayout(){
        val view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null, false)
        val dialog = AlertDialog.Builder(this)
        dialog.setView(view)
        dialog.create()

        dialog.show()
    }

    private fun customDialogFragment(){
        val dialogFragment = CustomDialogFragment()
        dialogFragment.show(supportFragmentManager, null)
    }

}