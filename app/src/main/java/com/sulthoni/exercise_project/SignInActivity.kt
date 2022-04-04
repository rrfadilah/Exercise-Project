package com.sulthoni.exercise_project

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import com.sulthoni.exercise_project.databinding.ActivitySignInBinding
import com.sulthoni.exercise_project.databinding.FragmentCostumDialogBinding


class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openPage()


        dialogAction()
        fun dialog() {
            val dialog = AlertDialog.Builder(this)
            val email = intent.getStringExtra("email")
            val password = intent.getStringExtra("password")
            val isiEmail = binding.editText
            val isiPassword = binding.editText2

            isiEmail.setText(email)
            isiPassword.setText(password)
            binding.btnGetStarted.setOnClickListener {
                if (isiEmail.text.toString().isEmpty()) {
                    Toast.makeText(this, "Kolom email masih kosong ", Toast.LENGTH_SHORT).show()
                    dialog.setTitle("Email masih kosong")
                    dialog.setMessage("Silakan isi email anda")
                    dialog.show()
                } else if (isiPassword.text.toString().isEmpty()) {
                    Toast.makeText(this, "Kolom passwoard masih kosong ", Toast.LENGTH_SHORT).show()
                    dialog.setTitle("Password tidak boleh kosong")
                    dialog.setMessage("Silakan isi Password anda")
                    dialog.show()
                } else if (isiPassword.text.toString().length < 8) {
                    Toast.makeText(this, "Password harus lebih dari 8 karakter", Toast.LENGTH_SHORT)
                        .show()
                    dialog.setTitle("Password lemah")
                    dialog.setMessage("Silakan isi password anda minimal 8 karakter")
                    dialog.show()
                } else if (!isiPassword.text.toString()
                        .matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))
                ) {
                    Toast.makeText(
                        this,
                        "Password harus mengandung upper case dan lowercase",
                        Toast.LENGTH_SHORT
                    ).show()
                    dialog.setTitle("Password lemah")
                    dialog.setMessage("Silakan isi password anda dengan\nmengandung uppercase dan lowercase")
                    dialog.show()
                } else if (!isiEmail.text.toString()
                        .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))
                ) {
                    Toast.makeText(this, "Email tidak valid", Toast.LENGTH_SHORT).show()
                    dialog.setTitle("Email tidak valid")
                    dialog.setMessage("Silakan isi email anda dengan benar")
                    dialog.show()
                }
            }
        }

        binding.tvlupa.setOnClickListener {
            Toast.makeText(this, "Tombol forgot password di pencet", Toast.LENGTH_SHORT).show()
        }
    }

    fun dialogAction() {
        val e_mail = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val isiEmail = binding.editText
        val isiPassword = binding.editText2

        isiEmail.setText(e_mail)
        isiPassword.setText(password)

        binding.btnGetStarted.setOnClickListener {
            val dialogAction = AlertDialog.Builder(this)
            if (isiEmail.text.toString().isEmpty() && isiPassword.text.toString().isEmpty()) {
                dialogAction.setTitle("Email dan Password kosong")
                dialogAction.setMessage("Silakan isi email dan password anda")
                dialogAction.setPositiveButton(
                    "Siap",
                    object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog?.dismiss()
                        }
                    })
                dialogAction.setNegativeButton("Tutup") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.setNeutralButton("Bantuan") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.show()
            } else if (!isiEmail.text.toString()
                    .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$")) && !isiEmail.text.toString()
                    .isEmpty()
            ) {
                dialogAction.setTitle("Email tidak valid")
                dialogAction.setMessage("Silakan isi email anda dengan benar")
                dialogAction.setPositiveButton(
                    "Siap",
                    object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog?.dismiss()
                        }
                    })
                dialogAction.setNegativeButton("Tutup") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.setNeutralButton("Bantuan") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.show()
            } else if (isiEmail.text.toString().isEmpty()) {
                dialogAction.setTitle("Email tidak boleh kosong")
                dialogAction.setMessage("Silakan isi email anda")
                dialogAction.setPositiveButton(
                    "Siap",
                    object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog?.dismiss()
                        }
                    })
                dialogAction.setNegativeButton("Tutup") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.setNeutralButton("Bantuan") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.show()
            } else if (isiPassword.text.toString().isEmpty()) {
                dialogAction.setTitle("Password tidak boleh kosong")
                dialogAction.setMessage("Silakan isi password anda")
                dialogAction.setPositiveButton(
                    "Siap",
                    object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog?.dismiss()
                        }
                    })
                dialogAction.setNegativeButton("Tutup") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.setNeutralButton("Bantuan") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.show()
            } else if (isiPassword.text.toString().length < 8) {
                dialogAction.setTitle("Password lemah")
                dialogAction.setMessage("Silakan isi password anda minimal 8 karakter")
                dialogAction.setPositiveButton(
                    "Siap",
                    object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog?.dismiss()
                        }
                    })
                dialogAction.setNegativeButton("Tutup") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.setNeutralButton("Bantuan") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.show()
            } else if (!isiPassword.text.toString()
                    .matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))
            ) {
                dialogAction.setTitle("Password lemah")
                dialogAction.setMessage("Silakan isi password anda dengan\nmengandung uppercase dan lowercase")
                dialogAction.setPositiveButton(
                    "Siap",
                    object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog?.dismiss()
                        }
                    })
                dialogAction.setNegativeButton("Tutup") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.setNeutralButton("Bantuan") { dialog, which ->
                    dialog.dismiss()
                }
                dialogAction.show()
            }
        }
    }

    fun dialogCustomLayout() {
        val view =
            LayoutInflater.from(this).inflate(R.layout.dialog_custom, null, false)
        val dialog = AlertDialog.Builder(this)
        dialog.setView(view)
        dialog.create().show()
    }

    fun dialogWithFragment() {
        val dialog = DialogFragment()
        dialog.show(supportFragmentManager, null)
    }

    fun openPage() {
        Toast.makeText(this, "Membuka halaman Sign In", Toast.LENGTH_LONG).show()
    }
}


