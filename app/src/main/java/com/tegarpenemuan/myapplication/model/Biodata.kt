package com.tegarpenemuan.myapplication.model

import java.io.Serializable

data class Biodata(
    val key: String,
    val phone: String,
    val email: String
) : Serializable