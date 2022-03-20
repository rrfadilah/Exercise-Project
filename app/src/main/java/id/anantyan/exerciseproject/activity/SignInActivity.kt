package id.anantyan.exerciseproject.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.databinding.ActivitySignInBinding
import id.anantyan.exerciseproject.model.DataDummy
import id.anantyan.utils.Constant.PASSING_TO_BASE_FRAGMENT
import id.anantyan.utils.Constant.PASSING_TO_SIGN_UP
import id.anantyan.utils.Validation.emailValid
import id.anantyan.utils.Validation.passwordValid
import id.anantyan.utils.validator.Validator
import id.anantyan.utils.validator.constant.Mode
import id.anantyan.utils.validator.validator
import id.anantyan.utils.viewbinding.viewBinding

class SignInActivity : AppCompatActivity() {

    private val binding: ActivitySignInBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }

    override fun onStart() {
        super.onStart()
        onBinding()
    }

    private fun onBinding() {
        binding.txtLayoutSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            intent.putExtra(PASSING_TO_SIGN_UP, true)
            startActivity(intent)
        }
        binding.btnSignIn.setOnClickListener {
            onValidation(this, onSignIn)
        }
    }

    private fun onValidation(context: Context, obj: Validator.OnValidateListener) {
        validator(context) {
            mode = Mode.SINGLE
            listener = obj
            validate(
                emailValid(binding.txtLayoutEmail),
                passwordValid(binding.txtLayoutPassword)
            )
        }
    }

    private val onSignIn = object : Validator.OnValidateListener {
        override fun onValidateSuccess(values: List<String>) {
            val item = DataDummy(
                email = binding.txtInputLayoutEmail.text.toString(),
                password = binding.txtInputLayoutPassword.text.toString()
            )
            val intent = Intent(this@SignInActivity, BaseFragmentActivity::class.java)
            intent.putExtra(PASSING_TO_BASE_FRAGMENT, item)
            startActivity(intent)
            finish()
        }

        override fun onValidateFailed(errors: List<String>) {}
    }
}

