package com.rizky.exercise_project

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.rizky.exercise_project.databinding.ActivitySignInBinding
import com.rizky.exercise_project.model.Biodata
import com.rizky.exercise_project.model.UserInfo

class SignInActivity : AppCompatActivity() {
    companion object {
        const val KEY = "KEY"
    }

    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Untuk menerima intent explicit
//        val valueFromAnotherActivity = intent.getStringExtra(Constant.Intent.EMAIL)

        // Untuk menerima intent bundle
//        intent.extras?.getString(Constant.Intent.PHONE)

        // Untuk menerima intent serializable
//        intent.extras?.getSerializable(Constant.Serialize.KEY) as Biodata

        // Untuk menerima intent parcelize
//        intent.extras?.getParcelable<UserInfo>(Constant.Parcelize.KEY)

        dialogWithFragment()
    }

    fun openPage() {
        Snackbar.make(binding.root, "Membuka Halaman Sign In", Snackbar.LENGTH_INDEFINITE)
            .setAction("Klik Disini") {
                // aksi yang akan kita jalan kan ketika klik di action nya...
                Toast.makeText(this, "Membuka halaman Sign In", Toast.LENGTH_LONG).show()
            }.show()
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
}