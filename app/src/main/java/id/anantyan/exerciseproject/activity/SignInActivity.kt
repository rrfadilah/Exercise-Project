package id.anantyan.exerciseproject.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.databinding.ActivitySignInBinding
import id.anantyan.exerciseproject.model.DataDummy
import id.anantyan.exerciseproject.utils.Constant.PASSING_TO_SIGN_UP
import id.anantyan.exerciseproject.utils.Validation.emailValid
import id.anantyan.exerciseproject.utils.Validation.passwordValid
import id.anantyan.exerciseproject.utils.validator.Validator
import id.anantyan.exerciseproject.utils.validator.constant.Mode
import id.anantyan.exerciseproject.utils.validator.validator
import id.anantyan.exerciseproject.utils.viewbinding.viewBinding

class SignInActivity : AppCompatActivity() {
    
    private val binding: ActivitySignInBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        onBinding()
    }

    private fun onBinding() {
        binding.txtLayoutSignUp.setOnClickListener {
            onValidation(this)
        }
        binding.btnSignIn.setOnClickListener {
            finish()
        }
    }

    private fun onValidation(context: Context) {
        validator(context) {
            mode = Mode.SINGLE
            listener = object : Validator.OnValidateListener {
                override fun onValidateSuccess(values: List<String>) {
                    val item = DataDummy(
                        email = binding.txtInputLayoutEmail.text.toString(),
                        password = binding.txtInputLayoutPassword.text.toString()
                    )
                    val intent = Intent(context, SignUpActivity::class.java)
                    intent.putExtra(PASSING_TO_SIGN_UP, item)
                    startActivity(intent)
                }

                override fun onValidateFailed(errors: List<String>) {}
            }
            validate(
                emailValid(binding.txtLayoutEmail),
                passwordValid(binding.txtLayoutPassword)
            )
        }
    }
}