package id.anantyan.exerciseproject.utils

import com.google.android.material.textfield.TextInputLayout
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.utils.validator.Validation
import id.anantyan.exerciseproject.utils.validator.rules.common.minimumLength
import id.anantyan.exerciseproject.utils.validator.rules.common.notEmpty
import id.anantyan.exerciseproject.utils.validator.rules.common.notNull
import id.anantyan.exerciseproject.utils.validator.rules.regex.email
import id.anantyan.exerciseproject.utils.validator.validation

object Validation {

    fun emailValid(textInputLayout: TextInputLayout): Validation {
        return validation(textInputLayout) {
            rules {
                +notNull(R.string.txt_not_null)
                +notEmpty(R.string.txt_not_empty)
                +email(R.string.txt_not_email)
            }
        }
    }

    fun passwordValid(textInputLayout: TextInputLayout): Validation {
        return validation(textInputLayout) {
            rules {
                +notNull(R.string.txt_not_null)
                +notEmpty(R.string.txt_not_empty)
                +minimumLength(8, R.string.txt_not_min_length)
            }
        }
    }

    fun generalValid(textInputLayout: TextInputLayout): Validation {
        return validation(textInputLayout) {
            rules {
                +notNull(R.string.txt_not_null)
                +notEmpty(R.string.txt_not_empty)
            }
        }
    }
}