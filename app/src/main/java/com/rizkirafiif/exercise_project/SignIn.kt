package com.rizkirafiif.exercise_project

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.rizkirafiif.exercise_project.model.Biodata
import com.rizkirafiif.exercise_project.model.UserInfo
import com.rizkirafiif.exercise_project.samplefragment.PicturesActivity
import java.util.*

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // Untuk menerima intent explicit
        //val valueFromAnotherActivity = intent.getStringExtra(Constant.Intent.EMAIL)

        // Untuk menerima intent bundle
        //intent.extras?.getString(Constant.Intent.PHONE)

        // Untuk menerima intent serializable
        //intent.extras?.getSerializable(Constant.Serialize.KEY) as Biodata

        // Untuk menerima intent parcelize
        //intent.extras?.getParcelable<UserInfo>(Constant.Parcelize.KEY)

        openPage()
        //authToast()

        // dialog standar
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Judul Dialog")
        //dialog.setTitle(R.string.versi_string_res)
        dialog.setMessage("Ini adalah dialog")
        //dialog.setMessage(R.string.versi_string_res)
        // kalau false ga bisa kelur dari tampilan dialog
        dialog.setCancelable(true)
        //dialog.show()

        // dialog action
        //dialogAction()

        //dialogCustom()

        //dialogFragment()

        authDialog()

    }

    fun openPage(){
        Toast.makeText(this, getString(R.string.buka_sign_in), Toast.LENGTH_LONG).show()
    }

    fun authToast(){
        val btnSignIn : Button = findViewById(R.id.btnSignIn)
        val etEmail : EditText = findViewById(R.id.etEmail)
        val etPassword : EditText = findViewById(R.id.etPassword)
        btnSignIn.setOnClickListener(){
            if (etEmail.text.toString().isEmpty()){
                Toast.makeText(this, getString(R.string.email_tidak_kosong), Toast.LENGTH_LONG).show()
            } else if (!etEmail.text.toString().contains("@")
                and !etEmail.text.toString().contains(".")){
                Toast.makeText(this, getString(R.string.email_invalid), Toast.LENGTH_LONG).show()
            } else if (etPassword.text.toString().isEmpty()){
                Toast.makeText(this, getString(R.string.password_tidak_kosong), Toast.LENGTH_LONG).show()
            } else if(etPassword.text.toString().length < 9){
                Toast.makeText(this, getString(R.string.password_kurang), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, getString(R.string.sukses_sign_in), Toast.LENGTH_LONG).show()
            }
        }
    }
    fun upperLowerChecker(){
        val input : EditText = findViewById(R.id.etPassword)
    }

    fun dialogAction(){
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Judul Dialog")
        dialog.setMessage("Isi dialog")
        dialog.setPositiveButton("Positif", object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                p0?.dismiss()
            }
        })
        dialog.setNegativeButton("Negatif") {
                dialogAksi, which ->
            dialogAksi.dismiss()
        }
        dialog.setNeutralButton("Neutral"){
                dialogAksi, which ->
            dialogAksi.dismiss()
        }
        dialog.show()
    }

    // dialog custom
    fun dialogCustom(){
        val view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null, false)
        val dialog = AlertDialog.Builder(this)
        dialog.setView(view)
        dialog.create().show()
    }

    // dialog fragment
    fun dialogFragment(){
        val dialog = CustomDialogFragment()
        dialog.show(supportFragmentManager, null)
    }

    // ---------------------------- // -------------------------------
    // Mini challenge alert dialog
    fun authDialog(){
        val btnSignIn : Button = findViewById(R.id.btnSignIn)
        val etEmail : EditText = findViewById(R.id.etEmail)
        val etPassword : EditText = findViewById(R.id.etPassword)
        btnSignIn.setOnClickListener() {
            if (etEmail.text.toString().isEmpty() and etPassword.text.toString().isEmpty()){
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Email")
                dialog.setMessage("Tidak boleh kosong")
                dialog.setCancelable(true)
                dialog.show()
                val dialog2 = AlertDialog.Builder(this)
                dialog2.setTitle("Password")
                dialog2.setMessage("Tidak boleh kosong")
                dialog2.setCancelable(true)
                dialog2.show()
            }else if (etEmail.text.toString().isEmpty()){
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Email")
                dialog.setMessage("Tidak boleh kosong")
                dialog.setCancelable(true)
                dialog.show()
            } else if (!etEmail.text.toString().contains("@")
                and !etEmail.text.toString().contains(".")){
                val view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null, false)
                val tvJudul : TextView = findViewById(R.id.tv_title)
                tvJudul.text = "Email invalid"
                val dialog = AlertDialog.Builder(this)
                dialog.setView(view)
                dialog.create().show()
            } else if (etPassword.text.toString().isEmpty()){
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Password")
                dialog.setMessage("Tidak boleh kosong")
                dialog.setCancelable(true)
                dialog.show()
            } else if(etPassword.text.toString().length < 9){
                val view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null, false)
                val tvJudul : TextView = findViewById(R.id.tv_title)
                tvJudul.text = "Password kurang dari 8 karakter"
                val dialog = AlertDialog.Builder(this)
                dialog.setView(view)
                dialog.create().show()
            } else {
                Toast.makeText(this, getString(R.string.sukses_sign_in), Toast.LENGTH_LONG).show()
            }
        }
    }
}