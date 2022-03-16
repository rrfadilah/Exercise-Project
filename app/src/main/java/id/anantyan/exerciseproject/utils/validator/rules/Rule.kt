package id.anantyan.exerciseproject.utils.validator.rules

import androidx.annotation.StringRes
import id.anantyan.exerciseproject.utils.validator.interfaces.ErrorImpl
import id.anantyan.exerciseproject.utils.validator.interfaces.Validate

abstract class Rule : ErrorImpl, Validate {

    constructor(@StringRes errorRes: Int) : super(errorRes)

    constructor(errorString: String) : super(errorString)
}

class RulesBuilder {
    val ruleList = arrayListOf<Rule>()

    operator fun Rule.unaryPlus() {
        ruleList.add(this)
    }
}
