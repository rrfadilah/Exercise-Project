package id.anantyan.exerciseproject.utils.validator.rules.regex

import androidx.annotation.StringRes
import id.anantyan.exerciseproject.utils.validator.rules.common.RegexRule

class AlphabetRule : RegexRule {

    constructor(@StringRes errorRes: Int) : super(ALPHABET_REGEX, errorRes)

    constructor(errorMessage: String) : super(ALPHABET_REGEX, errorMessage)

    companion object {
        private const val ALPHABET_REGEX = "^[a-zA-Z]*$"
    }
}

fun alphabetOnly(@StringRes errorRes: Int): AlphabetRule = AlphabetRule(errorRes)

fun alphabetOnly(errorMessage: String): AlphabetRule = AlphabetRule(errorMessage)