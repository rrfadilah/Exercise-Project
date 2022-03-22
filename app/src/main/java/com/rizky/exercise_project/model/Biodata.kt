package com.rizky.exercise_project.model

import java.io.Serializable

/**
 * com.rizky.exercise_project.model
 *
 * Created by Rizky Fadilah on 21/03/22.
 * https://github.com/rizkyfadilah
 *
 */

data class Biodata(
    val key: String,
    val phone: String,
    val email: String
) : Serializable
