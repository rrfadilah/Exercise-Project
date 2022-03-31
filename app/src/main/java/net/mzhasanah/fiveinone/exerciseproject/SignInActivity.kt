package net.mzhasanah.fiveinone.exerciseproject

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        openPage()

        val forgot = findViewById<TextView>(R.id.tvForgotPassword)
        forgot.setOnClickListener {
            Toast.makeText(this, R.string.ForgotPassword, Toast.LENGTH_SHORT).show()
        }

        dialogAction()
//        dialogCustomLayout()
//        dialogWithFragment()
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

//    fun dialogStandard(){
//        val e_mail = intent.getStringExtra("email")
//        val password = intent.getStringExtra("password")
//        val isiEmail = findViewById<EditText>(R.id.etEmail)
//        val isiPassword = findViewById<EditText>(R.id.etPassword)
//
//        isiEmail.setText(e_mail)
//        isiPassword.setText(password)
//
//        val btnlogin = findViewById<TextView>(R.id.btnSignIn)
//        btnlogin.setOnClickListener {
//            val dialog = AlertDialog.Builder(this)
//            if (!isiEmail.text.toString()
//                    .matches(Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$")) && !isiEmail.text.toString()
//                    .isEmpty()
//            ) {
////           Toast.makeText(this, R.string.EmailInvalid, Toast.LENGTH_SHORT).show()
//                dialog.setTitle("Email tidak valid")
//                dialog.setMessage("Silakan isi email anda dengan benar")
//                dialog.show()
//            } else if (isiEmail.text.toString().isEmpty()) {
//                Toast.makeText(this, R.string.EmailKosong, Toast.LENGTH_SHORT).show()
//                dialog.setTitle("Email tidak boleh kosong")
//                dialog.setMessage("Silakan isi email anda")
//                dialog.show()
//            } else if (isiPassword.text.toString().isEmpty()) {
//                Toast.makeText(this, R.string.PasswordKosong, Toast.LENGTH_SHORT).show()
//                dialog.setTitle("Password tidak boleh kosong")
//                dialog.setMessage("Silakan isi password anda")
//                dialog.show()
//            } else if (isiPassword.text.toString().length < 8) {
//                Toast.makeText(this, R.string.PasswordKurang, Toast.LENGTH_SHORT).show()
//                dialog.setTitle("Password lemah")
//                dialog.setMessage("Silakan isi password anda minimal 8 karakter")
//                dialog.show()
//            } else if (!isiPassword.text.toString().matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
//                Toast.makeText(this, R.string.PasswordUpLow, Toast.LENGTH_SHORT).show()
//                dialog.setTitle("Password lemah")
//                dialog.setMessage("Silakan isi password anda dengan\nmengandung uppercase dan lowercase")
//                dialog.show()
//            }
//        }
//    }

    fun dialogAction(){
        val e_mail = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val isiEmail = findViewById<EditText>(R.id.etEmail)
        val isiPassword = findViewById<EditText>(R.id.etPassword)

        isiEmail.setText(e_mail)
        isiPassword.setText(password)

        val btnlogin = findViewById<TextView>(R.id.btnSignIn)
        btnlogin.setOnClickListener {
            val dialogAction = AlertDialog.Builder(this)
            if(isiEmail.text.toString().isEmpty() && isiPassword.text.toString().isEmpty()){
                dialogAction.setTitle("Email dan Password kosong")
                dialogAction.setMessage("Silakan isi email dan password anda")
                dialogAction.setPositiveButton("Siap", object : DialogInterface.OnClickListener {
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
                dialogAction.setPositiveButton("Siap", object : DialogInterface.OnClickListener {
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
                dialogAction.setPositiveButton("Siap", object : DialogInterface.OnClickListener {
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
                dialogAction.setPositiveButton("Siap", object : DialogInterface.OnClickListener {
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
                dialogAction.setPositiveButton("Siap", object : DialogInterface.OnClickListener {
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
            } else if (!isiPassword.text.toString().matches(Regex("(?=.*[a-z])(?=.*[A-Z]).+"))) {
                dialogAction.setTitle("Password lemah")
                dialogAction.setMessage("Silakan isi password anda dengan\nmengandung uppercase dan lowercase")
                dialogAction.setPositiveButton("Siap", object : DialogInterface.OnClickListener {
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

    fun dialogCustomLayout(){
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_custom, null, false)
        val dialog = AlertDialog.Builder(this)
        dialog.setView(view)
        dialog.create().show()
    }

    fun dialogWithFragment(){
        val dialog = CustomDialogFragment()
        dialog.show(supportFragmentManager, null)
    }
}
