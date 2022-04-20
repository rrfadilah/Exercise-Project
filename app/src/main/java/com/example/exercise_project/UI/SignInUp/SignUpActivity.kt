package com.example.exercise_project.UI.SignInUp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.exercise_project.R
import com.example.exercise_project.UI.Data
import com.example.exercise_project.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val email = intent.extras?.getString(Data.EMAIL)
        //val pass = intent.extras?.getString(Data.PASS)

        //binding.etEmailSignUp.setText(email)
        //binding.etPassSignUp.setText(pass)

        binding.ibBack.setOnClickListener {
            val intentBack = Intent(this, SignInActivity::class.java)
            startActivity(intentBack)
        }

        binding.btnContinue.setOnClickListener {
            dataExist()
        }
    }

    private fun saveData() {
        val pref = this.getSharedPreferences(Data.Preferences.PREF_NAME, MODE_PRIVATE)

        val fullName = binding.etFullName.text.toString()
        val job = binding.etJob.text.toString()
        val email = binding.etEmailSignUp.text.toString()
        val pass = binding.etPassSignUp.text.toString()

        //untuk melakukan penyimpanan ke sharedpreferences hanya menggunakan code berikut ini
        val editor = pref.edit()
        editor.putString(Data.Preferences.PREF_FULLNAME, fullName)
        editor.putString(Data.Preferences.PREF_JOB, job)
        editor.putString(Data.Preferences.PREF_EMAIL, email)
        editor.putString(Data.Preferences.PREF_PASS, pass)
        editor.apply()

        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun dataExist() {
        val pref = this.getSharedPreferences(Data.Preferences.PREF_NAME, MODE_PRIVATE)

        val fullName = binding.etFullName.text.toString()
        val job = binding.etJob.text.toString()
        val email = binding.etEmailSignUp.text.toString()
        val pass = binding.etPassSignUp.text.toString()

        val prefName = pref.getString(Data.Preferences.PREF_FULLNAME, "")
        val prefJob = pref.getString(Data.Preferences.PREF_JOB, "")
        val prefEmail = pref.getString(Data.Preferences.PREF_EMAIL, "")
        val prefPass = pref.getString(Data.Preferences.PREF_PASS, "")

        if (fullName.isEmpty() && job.isEmpty() && email.isEmpty() && pass.isEmpty()){
            Toast.makeText(this, "Kolom register tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if(fullName.isEmpty()){
            Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if (fullName == prefName) {
            Toast.makeText(this, "Name already exist", Toast.LENGTH_SHORT).show()
        }else if(job.isEmpty()){
            Toast.makeText(this, "Job tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if (job == prefJob) {
            Toast.makeText(this, "Job already exist", Toast.LENGTH_SHORT).show()
        }else if(email.isEmpty()){
            Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if (email == prefEmail) {
            Toast.makeText(this, "Email already exist", Toast.LENGTH_SHORT).show()
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Email tidak valid", Toast.LENGTH_SHORT).show()
        }else if(pass.isEmpty()){
            Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if (pass == prefPass) {
            Toast.makeText(this, "Pass already exist", Toast.LENGTH_SHORT).show()
        }else if(pass.length < 8){
            Toast.makeText(this, "Password harus lebih dari 8 character", Toast.LENGTH_SHORT).show()
        }else if(!pass.matches(".*[A-Z].*".toRegex())){
            Toast.makeText(this, "Password harus mengandung 1 uppercase", Toast.LENGTH_SHORT).show()
        }else if(!pass.matches(".*[a-z].*".toRegex())){
            Toast.makeText(this, "Password harus mengandung 1 lowercase", Toast.LENGTH_SHORT).show()
        }else{
            val view = layoutInflater.inflate(R.layout.custom_dialog_signup, null)
            val dialogCustom = Dialog(this)
            dialogCustom.setContentView(view)
            dialogCustom.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialogCustom.show()

            view.findViewById<Button>(R.id.btnYa).setOnClickListener {
                saveData()
            }
            view.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
                dialogCustom.dismiss()
            }
        }
    }
}