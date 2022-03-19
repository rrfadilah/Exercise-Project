package id.anantyan.exerciseproject.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.databinding.ActivitySignUpBinding
import id.anantyan.exerciseproject.model.DataDummy
import id.anantyan.exerciseproject.utils.Constant
import id.anantyan.exerciseproject.utils.Constant.PASSING_TO_SIGN_UP
import id.anantyan.exerciseproject.utils.Validation.emailValid
import id.anantyan.exerciseproject.utils.Validation.generalValid
import id.anantyan.exerciseproject.utils.Validation.passwordValid
import id.anantyan.exerciseproject.utils.validator.Validator
import id.anantyan.exerciseproject.utils.validator.constant.Mode
import id.anantyan.exerciseproject.utils.validator.validator
import id.anantyan.exerciseproject.utils.viewbinding.viewBinding

class SignUpActivity : AppCompatActivity() {

    private val binding: ActivitySignUpBinding by viewBinding()
    private var item: DataDummy? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setSupportActionBar(binding.include.toolbar)
        supportActionBar?.setTitle(R.string.page_sign_up)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        onBinding()
    }

    private fun onBinding() {
        binding.btnContinue.setOnClickListener {
            onValidation(this)
        }
    }

    private fun onCheckIntent(): Boolean {
        return intent.hasExtra(PASSING_TO_SIGN_UP)
    }

    private fun onValidation(context: Context) {
        validator(context) {
            mode = Mode.SINGLE
            listener = object : Validator.OnValidateListener {
                override fun onValidateSuccess(values: List<String>) {
                    finish()
                }

                override fun onValidateFailed(errors: List<String>) {}
            }
            validate(
                generalValid(binding.txtLayoutFullName),
                generalValid(binding.txtLayoutPekerjaan),
                emailValid(binding.txtLayoutEmail),
                passwordValid(binding.txtLayoutPassword)
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (onCheckIntent()) {
            super.onBackPressed()
        } else {
            val intent = Intent(this, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            finish()
            startActivity(intent)
        }
    }
}