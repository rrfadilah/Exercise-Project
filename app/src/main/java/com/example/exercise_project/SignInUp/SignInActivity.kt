package com.example.exercise_project.SignInUp

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.exercise_project.Home.ActivityForHome
import com.example.exercise_project.R
import com.example.exercise_project.databinding.ActivitySignInBinding
import com.example.exercise_project.databinding.FragmentCustomDialogBinding

class SignInActivity : AppCompatActivity() {
    companion object {
        const val KEY = "KEY"
    }
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignIn2.setOnClickListener {
            //emailpassValidation()

            //untuk mengambil data dari sharedpreferences menggunakan code berikut ini
            //val prefEmail = pref.getString(Data.Preferences.PREF_EMAIL, "")
            //val prefPass = pref.getString(Data.Preferences.PREF_PASS, "")

            prefValidation()
        }

        binding.tvGoToSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val dialogAction = AlertDialog.Builder(this)
        dialogAction.setTitle("Warning!")
        dialogAction.setMessage("Apakah anda yakin untuk kembali? Data yang anda masukan tidak akan tersimpan")
        dialogAction.setPositiveButton("Ya"){ dialogYes, which ->
            finish()
        }
        dialogAction.setNegativeButton("Tidak"){ dialogNo, which ->
            dialogNo.dismiss()
        }
        dialogAction.setCancelable(false)
        dialogAction.show()
    }

    private fun prefValidation(){
        val pref = this.getSharedPreferences(Data.Preferences.PREF_NAME, MODE_PRIVATE)

        val prefEmail = pref.getString(Data.Preferences.PREF_EMAIL, "")
        val prefPass = pref.getString(Data.Preferences.PREF_PASS, "")

        val email = binding.etEmailSignIn.text.toString()
        val pass = binding.etPassSignIn.text.toString()

        if (email.isEmpty() && pass.isEmpty()){
            Toast.makeText(this, "Email dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if (email.isEmpty()) {
            Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if(email != prefEmail){
            Toast.makeText(this, "Email salah", Toast.LENGTH_SHORT).show()
        }else if (pass.isEmpty()) {
            Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if(pass != prefPass){
            Toast.makeText(this, "Password salah", Toast.LENGTH_SHORT).show()
        }else{
            val intent = Intent(this, ActivityForHome::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getBoolean(): Boolean {
        val pref = this.getSharedPreferences(Data.Preferences.PREF_NAME, MODE_PRIVATE)

        return pref.getBoolean(Data.Preferences.IS_LOGIN, false)
    }


    private fun loginSession() {

    }

    private fun emailpassValidationDialog(){
        val email = binding.etEmailSignIn.text.toString()
        val pass = binding.etPassSignIn.text.toString()

        if  (email.isEmpty() && pass.isEmpty()){
            val dialogAction = AlertDialog.Builder(this)
            dialogAction.setTitle("Email dan Password Tidak Boleh Kosong!")
            dialogAction.setMessage("Pastikan anda mengisi email dan password terlebih dahulu untuk masuk ke dalam Home")
            dialogAction.setPositiveButton("OK"){ dialogOK, which ->
                dialogOK.dismiss()
            }
            dialogAction.setCancelable(true)
            dialogAction.show()
        }else if (email.isEmpty()) {
            val view = layoutInflater.inflate(R.layout.custom_dialog_second, null)
            val dialogLayoutFirst = Dialog(this)
            dialogLayoutFirst.setContentView(view)
            dialogLayoutFirst.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialogLayoutFirst.show()

            view.findViewById<Button>(R.id.btnCustomLayoutEmail).setOnClickListener {
                dialogLayoutFirst.dismiss()
            }
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            val view = layoutInflater.inflate(R.layout.custom_dialog, null)
            val dialogLayoutSecond = Dialog(this)
            dialogLayoutSecond.setContentView(view)
            dialogLayoutSecond.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialogLayoutSecond.show()

            view.findViewById<Button>(R.id.btnCustomLayout).setOnClickListener {
                dialogLayoutSecond.dismiss()
            }
        }else if (pass.isEmpty()) {
            val view = layoutInflater.inflate(R.layout.custom_dialog_third, null)
            val dialogLayoutThird = Dialog(this)
            dialogLayoutThird.setContentView(view)
            dialogLayoutThird.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialogLayoutThird.show()

            view.findViewById<Button>(R.id.btnCustomLayoutPass).setOnClickListener {
                dialogLayoutThird.dismiss()
            }
        }else if (pass.length < 8){
            val dialogFragment = CustomDialogFragment()
            dialogFragment.show(supportFragmentManager, null)
        }else if (!pass.matches(".*[A-Z].*".toRegex())){
            val dialogValid = AlertDialog.Builder(this)
            dialogValid.setTitle("Password Harus Mengandung 1 Huruf Besar")
            dialogValid.setMessage("Pastikan password anda harus mengandung setidaknya 1 huruf besar")
            dialogValid.show()
        }else if (!pass.matches(".*[a-z].*".toRegex())){
            val dialogValid = AlertDialog.Builder(this)
            dialogValid.setTitle("Password Harus Mengandung 1 Huruf Kesar")
            dialogValid.setMessage("Pastikan password anda harus mengandung setidaknya 1 huruf kecil")
            dialogValid.show()
        }else{
            return
        }
    }

    private fun emailpassValidationToast(){
        val email = binding.etEmailSignIn.text.toString()
        val pass = binding.etPassSignIn.text.toString()

        if  (email.isEmpty() && pass.isEmpty()){
            Toast.makeText(this, "Email dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if (email.isEmpty()) {
            Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Email tidak valid", Toast.LENGTH_SHORT).show()
        }else if (pass.isEmpty()) {
            Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }else if (pass.length < 8){
            Toast.makeText(this, "Password harus lebih dari 8 character", Toast.LENGTH_SHORT).show()
        }else if (!pass.matches(".*[A-Z].*".toRegex())){
            Toast.makeText(this, "Password harus mengandung 1 uppercase", Toast.LENGTH_SHORT).show()
        }else if (!pass.matches(".*[a-z].*".toRegex())){
            Toast.makeText(this, "Password harus mengandung 1 lowercase", Toast.LENGTH_SHORT).show()
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