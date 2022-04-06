package com.tegarpenemuan.myapplication

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.tegarpenemuan.myapplication.databinding.ActivitySignBinding
import com.tegarpenemuan.myapplication.model.Biodata
import com.tegarpenemuan.myapplication.model.UserInfo
import com.tegarpenemuan.myapplication.utils.showCustomToast

class SignInActivity : AppCompatActivity() {

    companion object {
        const val KEY = "KEY"
    }

    lateinit var binding: ActivitySignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvCreateNewAccount.setOnClickListener {
            Toast(this).showCustomToast("Tombol create new accoiunt di pencet", this)
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.btnSignIn.setOnClickListener {
            dialogCustomLayout()
            validasiForm()
        }

        binding.tvForgotPassword.setOnClickListener {
            Toast(this).showCustomToast("Forgot Password Clicked", this)
        }

    }

    fun dialogStandard() {
        // Dialog standard
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("judul dialog")
        dialog.setMessage("isi pesan dialog")
        dialog.setCancelable(true)
        dialog.show()
    }

    fun dialogAction() {
        // dialog dengan action
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("judul dialog")
        dialog.setMessage("isi pesan dialog")
        dialog.setPositiveButton("POsitif", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.dismiss()
            }
        })
        dialog.setNegativeButton("Negatif") { dialog, which ->
            dialog.dismiss()
        }
        dialog.setNeutralButton("Netral") { dialog, which ->
            dialog.dismiss()
        }

        dialog.show()
    }

    fun dialogCustomLayout() {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_custom, null, false)
        val dialog = AlertDialog.Builder(this)
        dialog.setView(view)
        dialog.create().show()
    }

    fun dialogWithFragment() {
        val dialog = CustomDialogFragment()
        dialog.show(supportFragmentManager, null)
    }

    private fun validasiForm() {
        if (binding.etEmail.text.isEmpty()) {
            Toast(this).showCustomToast("Email tidak boleh kosong", this)
        } else if (!binding.etEmail.text.matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))
        ) {
            Toast(this).showCustomToast("Email Tidak Valid", this)
        } else if (binding.etPassword.text.isEmpty()) {
            Toast(this).showCustomToast("Password tidak boleh ksong", this)
        } else if (binding.etPassword.text.length < 8) {
            Toast(this).showCustomToast("Password harus lebih dari 8 karakter", this)
        } else if (!binding.etPassword.text.matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
            Toast(this).showCustomToast("Password harus mengandung upper case dan lowercase", this)
        } else {
            openPage()
        }
    }

    private fun openPage() {
        Snackbar.make(binding.root, "Selamat Anda Berhasil Login", Snackbar.LENGTH_INDEFINITE)
            .setAction("Logout") {
                Toast.makeText(this, "Anda Sudah Logout", Toast.LENGTH_LONG).show()
            }.show()
    }

    private fun IntentData() {
        // Untuk menerima intent explicit
        val valueFromAnotherActivity = intent.getStringExtra(Constant.Intent.EMAIL)

        // Untuk menerima intent bundle
        intent.extras?.getString(Constant.Intent.PHONE)

        // Untuk menerima intent serializable
        intent.extras?.getSerializable(Constant.Serialize.KEY) as Biodata

        // Untuk menerima intent parcelize
        intent.extras?.getParcelable<UserInfo>(Constant.Parcelize.KEY)
    }

    override fun onStop() {
        super.onStop()
        Toast(this).showCustomToast("Close Sign In", this)
    }
}