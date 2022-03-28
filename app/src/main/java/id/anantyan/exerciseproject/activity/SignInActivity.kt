package id.anantyan.exerciseproject.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.databinding.ActivitySignInBinding
import id.anantyan.exerciseproject.model.Users
import id.anantyan.utils.Constant.PASSING_TO_SIGN_UP_ACTIVITY
import id.anantyan.utils.Validation.emailValid
import id.anantyan.utils.Validation.passwordValid
import id.anantyan.utils.validator.Validator
import id.anantyan.utils.validator.constant.Mode
import id.anantyan.utils.validator.validator

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBinding()
    }

    private fun onBinding() {
        binding.txtLayoutSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            intent.putExtra(PASSING_TO_SIGN_UP_ACTIVITY, true)
            startActivity(intent)
        }
        binding.btnSignIn.setOnClickListener {
            onValidation(this, onSignIn)
        }
        binding.textView5.setOnClickListener {
            onToast(this, "Forgot password!")
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
            val item = Users(
                email = binding.txtInputLayoutEmail.text.toString(),
                password = binding.txtInputLayoutPassword.text.toString()
            )
            val intent = Intent(this@SignInActivity, BaseFragmentActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this@SignInActivity, item.toString(), Toast.LENGTH_SHORT).show()
        }

        override fun onValidateFailed(errors: List<String>) {
            onSnackbar(binding.root, errors[0])
        }
    }

    private fun onToast(context: Context, message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    private fun onSnackbar(viewContext: View, message: String) {
        Snackbar.make(viewContext, message, Snackbar.LENGTH_LONG).show()
    }
}

